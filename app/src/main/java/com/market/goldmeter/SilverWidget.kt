package com.market.goldmeter

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import kotlinx.android.synthetic.main.activity_main.s1
import kotlinx.android.synthetic.main.activity_main.s8
import org.jsoup.Jsoup
import java.io.IOException

/**
 * Implementation of App Widget functionality.
 */
class SilverWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateSilverWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateSilverWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    Thread(
        Runnable {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                val element = doc.select(".silver-rates.table.table-bordered.table-striped > tbody > tr:nth-of-type(1) > td:nth-of-type(2)")

                var final1 = element.text().toString().toFloat()
                var final8 = final1*1000

                val views = RemoteViews(context.packageName, R.layout.silver_widget)
                views.setTextViewText(R.id.sil_1gm, final1.toString() + "0")
                views.setTextViewText(R.id.sil_1, final8.toString() + "0")

                appWidgetManager.updateAppWidget(appWidgetId, views)

            } catch (e : IOException) {

            }
        }
    ).start()
}