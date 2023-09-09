package com.example.week1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

class MyCanvas : View {

    private val paint = Paint()
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 노란색 배경
        canvas.drawColor(Color.YELLOW)

        // 선 그리기
        paint.color = Color.RED
        paint.strokeWidth = 5F
        canvas.drawLine(100F, 100F, 500F, 200F, paint)

        paint.reset()

        // 원 그리기
        paint.color = Color.BLUE
        canvas.drawCircle(200F, 200F, 100F, paint)

        paint.strokeWidth = 5F
        paint.style = Paint.Style.STROKE
        canvas.drawRect(500F, 200F, 600F, 300F, paint)

        paint.textSize = 50F
        paint.typeface = Typeface.SERIF
        canvas.drawText("Hello", 100F, 350F, paint)

    }
}