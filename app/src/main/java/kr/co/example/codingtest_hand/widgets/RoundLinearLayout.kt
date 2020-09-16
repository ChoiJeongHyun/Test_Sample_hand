package kr.co.example.codingtest_hand.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import kr.co.example.codingtest_hand.R
import kr.co.example.codingtest_hand.utils.PLog

@BindingMethods(BindingMethod(type = RoundLinearLayout::class , attribute = "onLongClick" , method = "onLongClick"))
class RoundLinearLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr), View.OnLongClickListener {

    private var width: Float = 0f
    private var height: Float = 0f
    private var radius: Float = 0f
    private var cornerRadius: Float = 0f
    private var borderSize: Float = 0f

    private var paintBack: Paint = Paint()
    private var paintBoard: Paint = Paint()

    private var borderColorNormal: Int = Color.TRANSPARENT
    private var backgroundColorNormal: Int = Color.TRANSPARENT
    private var backgroundColorGradientStart: Int = Color.TRANSPARENT
    private var backgroundColorGradientEnd: Int = Color.TRANSPARENT

    private var alWaysMyTouch: Boolean = false

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.RoundLinearLayout, defStyleAttr, 0)
        cornerRadius = typeArray.getDimensionPixelSize(R.styleable.RoundLinearLayout_cornerRadius, 0).toFloat()
        borderSize = typeArray.getDimensionPixelSize(R.styleable.RoundLinearLayout_borderWidth, 0).toFloat()
        borderColorNormal = typeArray.getColor(R.styleable.RoundLinearLayout_borderColor, Color.TRANSPARENT)
        backgroundColorNormal = typeArray.getColor(R.styleable.RoundLinearLayout_backgroundColor, Color.TRANSPARENT)
        backgroundColorGradientStart = typeArray.getColor(
            R.styleable.RoundLinearLayout_backgroundColorNormalGradientStart, Color.TRANSPARENT
        )
        backgroundColorGradientEnd = typeArray.getColor(
            R.styleable.RoundLinearLayout_backgroundColorNormalGradientEnd, Color.TRANSPARENT
        )

        typeArray.recycle()
        viewInit()

    }

    private fun viewInit() {
        paintBack.apply {
            color = backgroundColorNormal
            style = Paint.Style.FILL
        }

        paintBoard.apply {
            color = borderColorNormal
            style = Paint.Style.STROKE
            strokeWidth = borderSize
        }
        //        super.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w.toFloat()
        height = h.toFloat()
        radius = cornerRadius
        if (radius == 0f) radius = if (height > width) (width - borderSize) / 2f else (height - borderSize) / 2f
        setMeasuredDimension(MeasureSpec.getSize(width.toInt()), MeasureSpec.getSize(height.toInt()))
    }

    override fun setBackgroundColor(color: Int) {
        backgroundColorNormal = color
        invalidate()
        //        super.setBackgroundColor(color)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        radius = cornerRadius
        if (radius == 0f) radius = if (height > width) (width - height) / 2f else (height - width) / 2f
        paintBack.color = backgroundColorNormal
        paintBoard.color = borderColorNormal

        if (backgroundColorGradientStart != Color.TRANSPARENT && backgroundColorGradientEnd != Color.TRANSPARENT) {
            val gradient = LinearGradient(
                0f, 0f, width, 0f, backgroundColorGradientStart, backgroundColorGradientEnd, Shader.TileMode.CLAMP
            )
            paintBack.isDither = true
            paintBack.shader = gradient
        }

        canvas?.drawRoundRect(RectF(borderSize / 2f, borderSize / 2f, width - borderSize / 2f, height - borderSize / 2f), radius, radius, paintBack)
        canvas?.drawRoundRect(RectF(borderSize / 2f, borderSize / 2f, width - borderSize / 2f, height - borderSize / 2f), radius, radius, paintBoard)

        super.dispatchDraw(canvas)
    }

    fun setAlWaysMyTouch(b: Boolean) {
        this.alWaysMyTouch = b
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (this.alWaysMyTouch) true else super.onInterceptTouchEvent(ev)
    }

    override fun onLongClick(p0: View?): Boolean {
        return false
    }

}