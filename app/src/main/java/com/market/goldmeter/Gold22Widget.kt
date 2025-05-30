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
class Gold22Widget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {


    Thread(
        Runnable {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                val element = doc.select("tr:nth-of-type(1) > td:nth-of-type(4)")

                val manipulatedElement = element.text().toString().filter{ it.isDigit() }

                var final1 =  manipulatedElement.toFloat()
                var final8 = final1*8

                // Construct the RemoteViews object
                val views = RemoteViews(context.packageName, R.layout.gold22_widget)
                views.setTextViewText(R.id.g22carat, final1.toString() + "0")
                views.setTextViewText(R.id.g22carat8, final8.toString() + "0")

                // Instruct the widget manager to update the widget
                appWidgetManager.updateAppWidget(appWidgetId, views)

            } catch (e : IOException) {

            }
        }
    ).start()


}