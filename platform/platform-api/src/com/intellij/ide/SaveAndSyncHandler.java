/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.ide;

import com.intellij.openapi.application.ApplicationManager;

/**
 * @author Kirill Likhodedov
 */
public abstract class SaveAndSyncHandler {
  public static SaveAndSyncHandler getInstance() {
    return ApplicationManager.getApplication().getComponent(SaveAndSyncHandler.class);
  }

  public abstract void saveProjectsAndDocuments();
  public abstract void scheduleRefresh();

  public abstract void saveAll();

  public abstract void refreshOpenFiles();

  public abstract void blockSaveOnFrameDeactivation();
  public abstract void unblockSaveOnFrameDeactivation();

  public abstract void blockSyncOnFrameActivation();
  public abstract void unblockSyncOnFrameActivation();
}
