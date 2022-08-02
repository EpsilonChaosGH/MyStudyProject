package com.example.mystudyproject

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mystudyproject.databinding.PartButtonsBinding

enum class BottomButtonAction{ POSITIVE, NEGATIVE }

typealias OnBottomButtonActionListener = (BottomButtonAction) -> Unit

class BottomButtonsView(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int):
    ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: PartButtonsBinding
    private var listener: OnBottomButtonActionListener? = null

        constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr,0)
        constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
        constructor(context: Context): this(context,null)



    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_buttons, this, true)
        binding = PartButtonsBinding.bind(this)
        initAttrs(context, attrs, defStyleAttr, defStyleRes)
        initListener()
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?, defStyleAttr: Int, desStyleRes: Int){
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomButtonsView, defStyleAttr, desStyleRes)

        val positiveButtonText = typedArray.getString(R.styleable.BottomButtonsView_bottomPositiveButtonText)
        setPositiveButtonText(positiveButtonText)

        val negativeButtonText = typedArray.getString(R.styleable.BottomButtonsView_bottomNegativeButtonText)
        setNegativeButtonText(negativeButtonText)

        val positiveButtonColor = typedArray.getColor(R.styleable.BottomButtonsView_bottomPositiveBackgroundColor, Color.BLACK)
        binding.btOk.backgroundTintList = ColorStateList.valueOf(positiveButtonColor)

        val negativeButtonColor = typedArray.getColor(R.styleable.BottomButtonsView_bottomNegativeBackgroundColor, Color.GREEN)
        binding.btCancel.backgroundTintList = ColorStateList.valueOf(negativeButtonColor)

        typedArray.recycle()
    }

    private fun initListener(){
        binding.btOk.setOnClickListener {
            this.listener?.invoke(BottomButtonAction.POSITIVE)
        }
        binding.btCancel.setOnClickListener {
            this.listener?.invoke(BottomButtonAction.NEGATIVE)
        }
    }

    fun setListener(listener: OnBottomButtonActionListener?){
        this.listener = listener
    }

    fun setPositiveButtonText(text: String?){
        binding.btOk.text = text ?: "OK"
    }

    fun setNegativeButtonText(text: String?){
        binding.btCancel.text = text ?: "CANCEL"
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()!!
        val savedState = SavedState(superState)
        savedState.positiveButtonText = binding.btOk.text.toString()
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)
        binding.btOk.text = savedState.positiveButtonText
    }

    class SavedState: BaseSavedState{

        var positiveButtonText: String? = null

        constructor(superState: Parcelable): super(superState)
        constructor(parcel: Parcel): super(parcel){
            positiveButtonText = parcel.readString()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeString(positiveButtonText)
        }
        companion object{
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState>{
                override fun createFromParcel(p0: Parcel): SavedState {
                    return SavedState(p0)
                }

                override fun newArray(p0: Int): Array<SavedState?> {
                    return Array(p0){ null }
                }
            }
        }
    }
}








