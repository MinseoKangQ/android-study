package com.example.week2_assignment

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable.RECTANGLE
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyView : View {

    private var shapeType: Int = RECTANGLE
    private var rect = Rect(0,0, 100, 100)
    private var circleRadius = 50f
    private var color = Color.BLUE
    private var paint = Paint()
    private var circleX = 0f // 원의 중심 X 좌표
    private var circleY = 0f // 원의 중심 Y 좌표

    companion object {
        const val RECTANGLE = 0
        const val CIRCLE = 1
    }
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = color
        when (shapeType) {
            RECTANGLE -> canvas.drawRect(rect ,paint)
            CIRCLE -> canvas.drawCircle(circleX, circleY, circleRadius, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (shapeType == RECTANGLE) {
            if (event.action == MotionEvent.ACTION_DOWN ||
                event.action == MotionEvent.ACTION_MOVE) {
                rect.left = event.x.toInt()
                rect.top = event.y.toInt()
                rect.right = rect.left + 100
                rect.bottom = rect.top + 100
                invalidate()

                return true
            }
        } else if (shapeType == CIRCLE) {
            if (event.action == MotionEvent.ACTION_DOWN ||
                event.action == MotionEvent.ACTION_MOVE) {
                // 원의 중심 좌표를 터치 위치로 설정
                circleX = event.x
                circleY = event.y
                invalidate()

                return true
            }
        }

        return super.onTouchEvent(event)
    }


    fun drawRectangle(){
        shapeType = RECTANGLE
        invalidate()
    }

    fun drawCircle(){
        shapeType = CIRCLE
        invalidate()
    }
}