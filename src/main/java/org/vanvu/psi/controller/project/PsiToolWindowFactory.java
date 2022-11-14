package org.vanvu.psi.controller.project;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;
import org.vanvu.psi.view.PsiPanel;

public class PsiToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        PsiProjectService component = PsiProjectService.getInstance(project);
        ContentManager contentManager = toolWindow.getContentManager();
        PsiPanel panel = component.initToolWindow(toolWindow);
        Content content = contentManager.getFactory().createContent(panel, null, false);
        contentManager.addContent(content);
    }

//    @Override
//    public boolean shouldBeAvailable(@NotNull Project project) {
//        return !project.isDefault() && PsiApplicationSettings.getInstance().PLUGIN_ENABLED;
//    }
}
