package com.market.goldmeter

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import org.jsoup.Jsoup
import java.io.IOException

/**
 * Implementation of App Widget functionality.
 */
class USDWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateUSDWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateUSDWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    Thread(
        Runnable {
            try {
                val doc = Jsoup.connect("https://www.xe.com/currencyconverter/convert/?Amount=1&From=USD&To=INR").get()
                val element = doc.getElementsByClass("sc-708e65be-1 chuBHG")

                val views = RemoteViews(context.packageName, R.layout.u_s_d_widget)
                views.setTextViewText(R.id.usd, element.text().substring(0,5))
                views.setTextViewText(R.id.updated, "Updated Today")

                appWidgetManager.updateAppWidget(appWidgetId, views)

            } catch (e : IOException) {

            }
        }
    ).start()
}