package com.example.minecraftmaniatowerdefense

import android.content.Context
import android.graphics.*
import android.view.SurfaceHolder


/**
 * Created by Sex_predator on 27.03.2016.
 */
class MyThread(context : Context,
               private val mSurfaceHolder: SurfaceHolder) : Thread() {

    private val REDRAW_TIME = 17 //частота обновления экрана - 10 мс

    private val mPaint: Paint

    val picture = BitmapFactory.decodeResource(context.resources, R.drawable.grass)
    var wood = BitmapFactory.decodeResource(context.resources, R.drawable.wood)
    val stive = BitmapFactory.decodeResource(context.resources, R.drawable.stive)
    val bow = BitmapFactory.decodeResource(context.resources, R.drawable.bow)
    val arrow = BitmapFactory.decodeResource(context.resources, R.drawable.arrow)
    var stone = BitmapFactory.decodeResource(context.resources, R.drawable.stone)
    var mRunning = false
    fun setRunning(running: Boolean) { //запускает и останавливает процесс
        mRunning = running
    }

    var mPrevRedrawTime = System.currentTimeMillis()
    val time: Long
        get() = System.currentTimeMillis()

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
    }

    override fun run() {
        var canvas: Canvas?

        while (mRunning) {
            val curTime = time

            val elapsedTime = curTime - mPrevRedrawTime
            if (elapsedTime < REDRAW_TIME) //проверяет, прошло ли 17 мс
                continue

            //если прошло, перерисовываем картинку
            canvas = null
            try {
                canvas = mSurfaceHolder.lockCanvas() //получаем canvas
                synchronized(mSurfaceHolder) {
                    draw(canvas) //функция рисования
                }
            } catch (e: NullPointerException) { /*если canvas не доступен*/
            } finally {
                if (canvas != null) mSurfaceHolder.unlockCanvasAndPost(canvas) //освобождаем canvas
            }
            mPrevRedrawTime = curTime
        }
    }

    private fun draw(canvas: Canvas) {
        val height = canvas.height
        var width = canvas.width
        for (i in 0..width step picture.height) {

            canvas.drawBitmap(
                    picture,
                    i.toFloat(),
                    (height - picture.height).toFloat(),
                    mPaint
            )
            drawTower(-10f,200f, canvas, mPaint)
        }


    }

    private fun drawTower(x: Float, y: Float, canvas: Canvas, paint : Paint) {

        canvas.drawBitmap(
                wood,
                x,
                y,
                paint
        )
        canvas.drawBitmap(
                wood,
                x ,
                y + wood.height,
                paint
        )
        drawShahta(1510f,-25f, canvas, mPaint)
    }
    private fun drawShahta(x: Float, y: Float, canvas: Canvas, paint : Paint) {
        canvas.drawBitmap(
                stone,
                x,
                y,
                paint
        )
        canvas.drawBitmap(
                stone,
                x ,
                y + stone.height,
                paint
        )
        canvas.drawBitmap(
                stone,
                x ,
                y + stone.height * 2,
                paint
        )
        canvas.drawBitmap(
                stone,
                x + stone.height,
                y + stone.height ,
                paint
        )
        canvas.drawBitmap(
        stone,
        x + stone.height,
        y + stone.height * 2,
        paint
        )
        canvas.drawBitmap(
                stone,
                x + stone.height * 2,
                y + stone.height * 2,
                paint
        )



        srive(-10f,240f, canvas, mPaint)
    }
    private fun srive(x: Float, y: Float, canvas: Canvas, paint : Paint){
        canvas.drawBitmap(
                stive,
                null,
                RectF(x+135,
                    canvas.height - picture.height - wood.height * 3.toFloat(),
                    x+wood.width -35,
                    canvas.height - picture.height - wood.height * 2.toFloat()
                ),

                paint
        )




    }

}




