// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.jetbrains.kotlin.idea.inspections

import com.intellij.codeInspection.*
import org.jetbrains.kotlin.idea.KotlinBundle
import org.jetbrains.kotlin.idea.core.util.isOneLiner
import org.jetbrains.kotlin.idea.intentions.branchedTransformations.getWhenConditionSubjectCandidate
import org.jetbrains.kotlin.idea.intentions.branchedTransformations.intentions.IfToWhenIntention
import org.jetbrains.kotlin.idea.intentions.branchedTransformations.isElseIf
import org.jetbrains.kotlin.idea.intentions.branches
import org.jetbrains.kotlin.idea.util.psi.patternMatching.matches
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.anyDescendantOfType
import org.jetbrains.kotlin.psi.psiUtil.lastBlockStatementOrThis

class CascadeIfInspection : AbstractKotlinInspection(), CleanupLocalInspectionTool {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean, session: LocalInspectionToolSession) =
        ifExpressionVisitor(fun(expression) {
            val branches = expression.branches
            if (branches.size <= 2) return
            if (expression.isOneLiner()) return

            if (branches.any {
                    it == null || it.lastBlockStatementOrThis() is KtIfExpression
                }
            ) return

            if (expression.isElseIf()) return

            if (expression.anyDescendantOfType<KtExpressionWithLabel> {
                    it is KtBreakExpression || it is KtContinueExpression
                }
            ) return

            var current: KtIfExpression? = expression
            var lastSubjectCandidate: KtExpression? = null
            while (current != null) {
                val subjectCandidate = current.condition.getWhenConditionSubjectCandidate(checkConstants = false) ?: return
                if (lastSubjectCandidate != null && !lastSubjectCandidate.matches(subjectCandidate)) return
                lastSubjectCandidate = subjectCandidate
                current = current.`else` as? KtIfExpression
            }

            holder.registerProblem(
                expression.ifKeyword,
                KotlinBundle.message("cascade.if.should.be.replaced.with.when"),
                ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
                IntentionWrapper(IfToWhenIntention())
            )
        })
}
