import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

val ViewGroup.inflater
get() = LayoutInflater.from(context)

fun ViewGroup.inflate(@LayoutRes res:Int) =
    inflater.inflate(res, this, false)

fun String.parseColor() = Color.parseColor(this)