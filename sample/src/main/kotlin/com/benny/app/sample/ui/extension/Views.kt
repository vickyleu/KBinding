package com.benny.app.sample.ui.extension

import android.content.Context
import android.support.v7.view.ContextThemeWrapper
import android.view.View
import android.view.ViewManager
import android.widget.ProgressBar
import com.benny.app.sample.ui.widget.ViewPagerIndicator
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.appcompat.v7._Toolbar
import org.jetbrains.anko.appcompat.v7.`$$Anko$Factories$AppcompatV7ViewGroup`
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.internals.AnkoInternals

/**
 * Created by benny on 12/21/15.
 */

var View.backgroundColorResource: Int
    get() = throw UnsupportedOperationException()
    set(value) { this.backgroundColor = context.resources.getColor(value) }

fun ViewManager.simpleDraweeView(init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return ankoView({ctx: Context -> SimpleDraweeView(ctx)}) { init() }
}

fun ViewManager.viewPagerIndicator(init: ViewPagerIndicator.() -> Unit): ViewPagerIndicator {
    return ankoView({ctx: Context -> ViewPagerIndicator(ctx)}) { init() }
}

fun ViewManager.progressBar(style: Int): ProgressBar {
    return ankoView({ctx: Context -> ProgressBar(ctx, null, style) }) { }
}
fun ViewManager.progressBar(style: Int, init: ProgressBar.() -> Unit): ProgressBar {
    return ankoView({ctx: Context -> ProgressBar(ctx, null, style) }) { init() }
}

inline fun <T : View> ViewManager.ankoView(theme: Int, factory: (ctx: Context) -> T, init: T.() -> Unit): T {
    val ctx = AnkoInternals.getContext(this)
    val view = factory(ContextThemeWrapper(ctx, theme))
    view.init()
    AnkoInternals.addView(this, view)
    return view
}

inline fun ViewManager.toolbar(theme: Int, init: _Toolbar.() -> Unit): android.support.v7.widget.Toolbar {
    return ankoView(theme, `$$Anko$Factories$AppcompatV7ViewGroup`.TOOLBAR) { init() }
}