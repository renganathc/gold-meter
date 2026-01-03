package com.market.goldmeter

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import org.jsoup.Jsoup
import java.io.IOException

class USDWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateUSDWidget(context, appWidgetManager, appWidgetId)
        }
    }
}

internal fun updateUSDWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    Thread {
        val views = RemoteViews(context.packageName, R.layout.u_s_d_widget)

        try {
            val doc = Jsoup
                .connect("https://www.xe.com/currencyconverter/convert/?Amount=1&From=USD&To=INR")
                .get()

            val element = doc.getElementsByClass("sc-708e65be-1 chuBHG")
            val rawText = element.text()

            val displayText = if (rawText.isNotBlank()) {
                rawText.take(5)
            } else {
                "--"
            }

            views.setTextViewText(R.id.usd, displayText)
            views.setTextViewText(R.id.updated, "Updated Today")

        } catch (e: IOException) {
            views.setTextViewText(R.id.usd, "--")
            views.setTextViewText(R.id.updated, "Update failed")
        }

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }.start()
}
