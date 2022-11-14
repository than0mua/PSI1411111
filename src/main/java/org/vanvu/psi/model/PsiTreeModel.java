package org.vanvu.psi.model;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import org.vanvu.psi.controller.project.PsiProjectService;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class PsiTreeModel implements TreeModel {
    private final PsiProjectService myPsiProjectService;
    private PsiElement myRootElement;

    public PsiTreeModel(PsiProjectService projectComponent) {
        myPsiProjectService = projectComponent;
    }

    public Object getRoot() {
        return myRootElement;
    }

    public void setRoot(PsiElement root) {
        myRootElement = root;
    }

    public Object getChild(Object parent, int index) {
        PsiElement psi = (PsiElement) parent;
        List<PsiElement> children = getFilteredChildren(psi);
        return children.get(index);
    }

    public int getChildCount(Object parent) {
        PsiElement psi = (PsiElement) parent;
        return getFilteredChildren(psi).size();
    }

    public boolean isLeaf(Object node) {
        PsiElement psi = (PsiElement) node;
        return getFilteredChildren(psi).size() == 0;
    }

    private List<PsiElement> getFilteredChildren(PsiElement psi) {
        final List<PsiElement> filteredChildren = new ArrayList<>();

        for (PsiElement e = psi.getFirstChild(); e != null; e = e.getNextSibling())
            if (isValid(e)) {
                filteredChildren.add(e);
            }

        return filteredChildren;
    }

    private boolean isValid(PsiElement psiElement) {
        return !myPsiProjectService.isFilterWhitespace() || !(psiElement instanceof PsiWhiteSpace);
    }

    public int getIndexOfChild(Object parent, Object child) {
        PsiElement psiParent = (PsiElement) parent;
        List<PsiElement> psiChildren = getFilteredChildren(psiParent);

        return psiChildren.indexOf(child);
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    public synchronized void addTreeModelListener(TreeModelListener l) {
    }

    public synchronized void removeTreeModelListener(TreeModelListener l) {
    }
}
