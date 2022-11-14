package org.vanvu.psi.controller.application;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@State(name = "PsiPluginSettings", storages = @Storage("other.xml"))
public class PsiApplicationSettings implements PersistentStateComponent<PsiApplicationSettings> {
    private final TextAttributes myTextAttributes = new TextAttributes();
    public String HIGHLIGHT_COLOR;
    public String REFERENCE_HIGHLIGHT_COLOR;
    public boolean PLUGIN_ENABLED;
    private final TextAttributes myReferenceTextAttributes = new TextAttributes();

    public PsiApplicationSettings() {
        HIGHLIGHT_COLOR = "162 3 229 32";
        REFERENCE_HIGHLIGHT_COLOR = "162 229 3 32";
        PLUGIN_ENABLED = true;
        getTextAttributes().setBackgroundColor(parseColor(HIGHLIGHT_COLOR));
    }
    public static Color parseColor(String rgba)
    {
        int red = 0, green = 0, blue = 0, alpha = 128;
        String token[] = rgba.split(" ");
        if (token.length > 0) red = getSample(token[0]);
        if (token.length > 1) green = getSample(token[1]);
        if (token.length > 2) blue = getSample(token[2]);
        if (token.length > 3) alpha = getSample(token[3]);
        return new Color(red, green, blue, alpha);
    }
    private static int getSample(String sample)
    {
        int s;
        try
        {
            s = Math.min(Math.abs(Integer.valueOf(sample).intValue()), 255);
        }
        catch (NumberFormatException e)
        {
            s = 0;
        }
        return s;
    }
    public static PsiApplicationSettings getInstance() {
        return ServiceManager.getService(PsiApplicationSettings.class);
    }

    @Nullable
    @Override
    public PsiApplicationSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PsiApplicationSettings state) {
        XmlSerializerUtil.copyBean(state, this);

        getTextAttributes().setBackgroundColor(parseColor(HIGHLIGHT_COLOR));
        getReferenceTextAttributes().setBackgroundColor(parseColor(REFERENCE_HIGHLIGHT_COLOR));
    }

    public TextAttributes getTextAttributes() {
        return myTextAttributes;
    }

    public TextAttributes getReferenceTextAttributes() {
        return myReferenceTextAttributes;
    }
}
