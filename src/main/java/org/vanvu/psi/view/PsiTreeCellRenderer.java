package org.vanvu.psi.view;

import com.intellij.icons.AllIcons;
import com.intellij.psi.*;
import com.intellij.psi.xml.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

class PsiTreeCellRenderer extends DefaultTreeCellRenderer {
    private final ElementVisitor _elementVisitor = new ElementVisitor();
    private final XmlElementVisitor _elementVisitorXml = new ElementVisitorXml();

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean isExpanded,
                                                  boolean isLeaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, isSelected, isExpanded, isLeaf, row, hasFocus);
        setIcon(AllIcons.General.Information);

        PsiElement psiElement = (PsiElement) value;

        psiElement.accept(_elementVisitor);
        psiElement.accept(_elementVisitorXml);

        return this;
    }

    public PsiTreeCellRenderer() {
        setOpaque(false);
    }

    private class ElementVisitor extends PsiElementVisitor {

        private static final int MAX_TEXT_LENGTH = 80;


        public void visitBinaryFile(PsiBinaryFile psiElement) {
            setIcon(AllIcons.General.Information);
            setText("PsiBinaryFile: " + psiElement.getName());
        }


        public void visitComment(PsiComment psiElement) {
            setIcon(AllIcons.General.Information);
            setText("PsiComment: " + truncate(psiElement.getText()));
        }

        public void visitDirectory(PsiDirectory psiElement) {
            setIcon(AllIcons.General.Information);
            setText("PsiDirectory: " + psiElement.getName());
        }

        public void visitElement(PsiElement psiElement) {
            setText(psiElement.toString());
        }


        public void visitFile(PsiFile psiElement) {
            setText("PsiFile: " + psiElement.getName());
        }


        public void visitPlainTextFile(PsiPlainTextFile psiElement) {
            setIcon(AllIcons.General.Information);
            setText("PsiPlainTextFile: " + psiElement.getName());
        }


        public void visitWhiteSpace(@NotNull PsiWhiteSpace psiElement) {
            setIcon(AllIcons.General.Information);
            setText("PsiWhiteSpace");
        }

        private String truncate(String text) {
            if (text.length() > MAX_TEXT_LENGTH)
                return text.substring(0, MAX_TEXT_LENGTH).trim() + "...";
            else
                return text;
        }

        private ElementVisitor() {
        }
    }



    private class ElementVisitorXml extends XmlElementVisitor {
        public void visitXmlAttribute(XmlAttribute psiElement) {
            setIcon(AllIcons.General.Information);
            setText("XmlAttribute: " + psiElement.getName());
        }

        public void visitXmlAttributeValue(XmlAttributeValue psiElement) {
            setText("XmlAttributeValue");
        }

        public void visitXmlComment(XmlComment psiElement) {
            setIcon(AllIcons.General.Information);
            setText("XmlComment");
        }

        public void visitXmlDecl(XmlDecl psiElement) {
            setText("XmlDecl");
        }

        public void visitXmlDoctype(XmlDoctype psiElement) {
            setText("XmlDoctype");
        }

        public void visitXmlDocument(XmlDocument psiElement) {
            setText("XmlDocument");
        }

        public void visitXmlFile(XmlFile psiElement) {
            setIcon(AllIcons.General.Information);
            setText("XmlFile: " + psiElement.getName());
        }

        public void visitXmlProlog(XmlProlog psiElement) {
            setText("XmlProlog");
        }

        public void visitXmlTag(XmlTag psiElement) {
            setIcon(AllIcons.General.Information);
            setText("XmlTag: " + psiElement.getName());
        }

        public void visitXmlToken(XmlToken psiElement) {
            setText("XmlToken: " + psiElement.getText());
        }
    }

}