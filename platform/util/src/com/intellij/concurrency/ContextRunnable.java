// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.concurrency;

import com.intellij.openapi.application.AccessToken;
import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;

final class ContextRunnable implements Runnable {

  private final @NotNull CoroutineContext myParentContext;
  private final @NotNull Runnable myRunnable;

  ContextRunnable(@NotNull Runnable runnable) {
    myParentContext = ThreadContext.currentThreadContext();
    myRunnable = runnable;
  }

  @Override
  public void run() {
    ThreadContext.checkUninitializedThreadContext();
    try (AccessToken ignored = ThreadContext.replaceThreadContext(myParentContext)) {
      myRunnable.run();
    }
  }
}
