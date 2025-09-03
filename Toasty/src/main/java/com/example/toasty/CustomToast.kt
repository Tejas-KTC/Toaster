package com.example.toasty

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class CustomToast private constructor(
    private val context: Context,
    private val message: String,
    private val duration: Int,
    private val bgColor: Int,
    private val textColor: Int,
    private val icon: Drawable?,
    private val gravity: Int
) {

    fun show() {
        val inflater = LayoutInflater.from(context)
        val layout: View = inflater.inflate(R.layout.toast_layout, null)

        val textView: TextView = layout.findViewById(R.id.toast_text)
        val iconView: ImageView = layout.findViewById(R.id.toast_icon)

        textView.text = message
        textView.setTextColor(textColor)
        layout.setBackgroundColor(bgColor)

        if (icon != null) {
            iconView.setImageDrawable(icon)
            iconView.visibility = View.VISIBLE
        } else {
            iconView.visibility = View.GONE
        }

        val toast = Toast(context)
        toast.duration = duration
        toast.view = layout
        toast.setGravity(gravity, 0, 100)
        toast.show()
    }

    class Builder(private val context: Context) {
        private var message: String = ""
        private var duration: Int = Toast.LENGTH_SHORT
        private var bgColor: Int = Color.DKGRAY
        private var textColor: Int = Color.WHITE
        private var icon: Drawable? = null
        private var gravity: Int = Gravity.BOTTOM

        fun setMessage(msg: String) = apply { this.message = msg }
        fun setDuration(dur: Int) = apply { this.duration = dur }
        fun setBackgroundColor(color: Int) = apply { this.bgColor = color }
        fun setTextColor(color: Int) = apply { this.textColor = color }
        fun setIcon(resId: Int) = apply { this.icon = ContextCompat.getDrawable(context, resId) }
        fun setGravity(gravity: Int) = apply { this.gravity = gravity }

        fun build(): CustomToast {
            return CustomToast(context, message, duration, bgColor, textColor, icon, gravity)
        }
    }
}
