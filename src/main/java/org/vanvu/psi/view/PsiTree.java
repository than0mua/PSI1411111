package org.vanvu.psi.view;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

public class PsiTree extends JTree
{
    public PsiTree(TreeModel newModel)
    {
        super(newModel);
        setCellRenderer(new PsiTreeCellRenderer());
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setExpandsSelectedPaths(true);
    }
}
