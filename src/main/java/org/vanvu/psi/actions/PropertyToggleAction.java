package org.vanvu.psi.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import org.jetbrains.annotations.NotNull;
import org.vanvu.psi.controller.project.PsiProjectService;

import javax.swing.*;
import java.util.function.BooleanSupplier;

public class PropertyToggleAction extends ToggleAction {
    @NotNull
    private final PsiProjectService.BooleanConsumer myMutator;
    @NotNull
    private final BooleanSupplier myAccessor;

    public PropertyToggleAction(String actionName, String toolTip, Icon icon,
                                @NotNull BooleanSupplier accessor,
                                @NotNull PsiProjectService.BooleanConsumer mutator) {
        super(actionName, toolTip, icon);
        myAccessor = accessor;
        myMutator = mutator;

    }

    public boolean isSelected(@NotNull AnActionEvent anactionevent) {
        return myAccessor.getAsBoolean();
    }

    public void setSelected(@NotNull AnActionEvent anactionevent, boolean flag) {
        myMutator.accept(flag);
    }
}
