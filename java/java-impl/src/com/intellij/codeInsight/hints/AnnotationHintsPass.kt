// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.codeInsight.hints

import com.intellij.codeHighlighting.EditorBoundHighlightingPass
import com.intellij.codeInsight.CodeInsightSettings
import com.intellij.codeInsight.ExternalAnnotationsManager
import com.intellij.codeInsight.InferredAnnotationsManager
import com.intellij.codeInsight.daemon.impl.HintRenderer
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ex.util.CaretVisualPositionKeeper
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiModifierListOwner
import com.intellij.psi.SyntaxTraverser
import com.intellij.util.DocumentUtil
import gnu.trove.TIntObjectHashMap

class AnnotationHintsPass(private val rootElement: PsiElement, editor: Editor) : EditorBoundHighlightingPass(editor,
                                                                                                             rootElement.containingFile,
                                                                                                             true) {
  private val hints = TIntObjectHashMap<MutableList<HintData>>()
  private val traverser: SyntaxTraverser<PsiElement> = SyntaxTraverser.psiTraverser(rootElement)

  override fun doCollectInformation(progress: ProgressIndicator) {
    assert(myDocument != null)
    hints.clear()

    val virtualFile = rootElement.containingFile?.originalFile?.virtualFile

    if ((CodeInsightSettings.getInstance().SHOW_EXTERNAL_ANNOTATIONS_INLINE &&
         virtualFile != null &&
         ExternalAnnotationsManager.getInstance(myProject).hasAnnotationRootsForFile(virtualFile))
        ||
        CodeInsightSettings.getInstance().SHOW_INFERRED_ANNOTATIONS_INLINE) {
      traverser.forEach { process(it) }
    }
  }

  private fun process(element: PsiElement) {
    if (element is PsiModifierListOwner) {
      var annotations = emptySequence<PsiAnnotation>()
      if (CodeInsightSettings.getInstance().SHOW_EXTERNAL_ANNOTATIONS_INLINE) {
        annotations += ExternalAnnotationsManager.getInstance(myProject).findExternalAnnotations(element).orEmpty()
      }
      if (CodeInsightSettings.getInstance().SHOW_INFERRED_ANNOTATIONS_INLINE) {
        annotations += InferredAnnotationsManager.getInstance(myProject).findInferredAnnotations(element)
      }

      annotations.forEach {
        if (it.nameReferenceElement != null && element.modifierList != null) {
          val offset = element.modifierList!!.textRange.startOffset
          var hintList = hints.get(offset)
          if (hintList == null) {
            hintList = arrayListOf()
            hints.put(offset, hintList)
          }
          hintList.add(HintData("@" + it.nameReferenceElement?.referenceName + it.parameterList.text))
        }
      }
    }
  }

  override fun doApplyInformationToEditor() {
    val keeper = CaretVisualPositionKeeper(myEditor)

    DocumentUtil.executeInBulk(myEditor.document, hints.size() > 1000) {
      val inlayModel = myEditor.inlayModel
      inlayModel.getInlineElementsInRange(rootElement.textRange.startOffset + 1, rootElement.textRange.endOffset - 1)
        .filter { ANNOTATION_INLAY_KEY.isIn(it) }
        .forEach { Disposer.dispose(it) }

      hints.forEachEntry { offset, info ->
        info.forEach {
          val inlay = inlayModel.addInlineElement(offset, AnnotationHintRenderer(it.presentationText))
          inlay.putUserData(ANNOTATION_INLAY_KEY, true)
        }
        true
      }
    }

    keeper.restoreOriginalLocation(false)

    if (rootElement === myFile) {
      AnnotationHintsPassFactory.putCurrentModificationStamp(myEditor, myFile)
    }
  }

  class HintData(val presentationText: String)

  companion object {
    private val ANNOTATION_INLAY_KEY = Key.create<Boolean>("ANNOTATION_INLAY_KEY")
  }

  private class AnnotationHintRenderer(text: String) : HintRenderer(text) {
    override fun getContextMenuGroupId() = "AnnotationHintsContextMenu"
  }

  class ToggleExternalAnnotationsHintsAction : ToggleAction() {
    override fun isSelected(e: AnActionEvent?): Boolean = CodeInsightSettings.getInstance().SHOW_EXTERNAL_ANNOTATIONS_INLINE

    override fun setSelected(e: AnActionEvent?, state: Boolean) {
      CodeInsightSettings.getInstance().SHOW_EXTERNAL_ANNOTATIONS_INLINE = state
      AnnotationHintsPassFactory.forceHintsUpdateOnNextPass()
    }
  }

  class ToggleInferredAnnotationsHintsAction : ToggleAction() {
    override fun isSelected(e: AnActionEvent?): Boolean = CodeInsightSettings.getInstance().SHOW_INFERRED_ANNOTATIONS_INLINE

    override fun setSelected(e: AnActionEvent?, state: Boolean) {
      CodeInsightSettings.getInstance().SHOW_INFERRED_ANNOTATIONS_INLINE = state
      AnnotationHintsPassFactory.forceHintsUpdateOnNextPass()
    }
  }
}