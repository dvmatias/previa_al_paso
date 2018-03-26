package utils.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.previaalpaso.matiasdelvecchio.previaalpaso.R;

/**
 * Created by cesar.delvecchio on 21/03/2018.
 */

public class SplashTitleExplanation extends LinearLayout {
    /**
     * TAG.
     */
    private static final String TAG = SplashTitleExplanation.class.getSimpleName();

    /**
     * View Title.
     */
    private TextView title;

    /**
     * View Separator.
     */
    private View separator;

    /**
     * View Explanation.
     */
    private TextView explanation;

    /**
     * Attribute Title
     */
    private String attrTitle;

    /**
     * Attribute Explanation
     */
    private String attrExplanation;

    /**
     * Default attribute set.
     */
    private final AttributeSet DEF_ATTR = null;

    /**
     * Default style.
     */
    private final int DEF_STYLE = 0;

    /**
     * Constructor.
     *
     * @param context [Context] context.
     */
    public SplashTitleExplanation(Context context) {
        super(context);
        if(!isInEditMode()) init(context, DEF_ATTR, DEF_STYLE);
    }

    /**
     * Constructor.
     *
     * @param context [Context] context.
     * @param attrs   [AttributeSet] Attribute set.
     */
    public SplashTitleExplanation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode()) init(context, attrs, DEF_STYLE);
    }

    /**
     * Constructor.
     *
     * @param context       [Context] context.
     * @param attrs         [AttributeSet] Attribute set.
     * @param defStyleAttr  [int] Default style.
     */
    public SplashTitleExplanation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(!isInEditMode()) init(context, attrs, defStyleAttr);
    }

    /**
     * Init.
     *
     * @param context       [Context] context.
     * @param attrs         [AttributeSet] Attribute set.
     * @param defStyleAttr  [int] Default style.
     */
    private void init (Context context, AttributeSet attrs, int defStyleAttr) {
        View rootView = inflate(context, R.layout.customview_splash_title_explanation, this);

        title = rootView.findViewById(R.id.title);
        separator = rootView.findViewById(R.id.separator);
        explanation = rootView.findViewById(R.id.explanation);

        setAttributes(context, attrs);
        setTitle();
        setExplanation();
    }

    private void setAttributes(Context context, AttributeSet attrs) {
        attrTitle = "";
        attrExplanation = "";

        if (attrs != null) {
            TypedArray ta = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.ValueSplashTitleExplanation, 0, 0);
            attrTitle = ta.getString(R.styleable.ValueSplashTitleExplanation_titleText);
            attrExplanation = ta.getString(R.styleable.ValueSplashTitleExplanation_explanationText);

            ta.recycle();
        }
    }

    private void setTitle() {
        title.setText(attrTitle);
    }

    private void setExplanation() {
        explanation.setText(attrExplanation);
    }
}
