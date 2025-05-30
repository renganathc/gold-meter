package com.market.goldmeter

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import kotlinx.android.synthetic.main.activity_main.dollar1
import kotlinx.android.synthetic.main.activity_main.last_update
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
                val doc = Jsoup.connect("https://www.google.com/search?q=usd+to+inr&rlz=1C5CHFA_enIN1052IN1052&oq=usd+to+inr&gs_lcrp=EgZjaHJvbWUqBggAEEUYOzIGCAAQRRg7MgYIARBFGEAyBggCEEUYPDIGCAMQRRg8MgYIBBBFGDzSAQgxMzUxajBqN6gCALACAA&sourceid=chrome&ie=UTF-8").get()
                val element = doc.select(".DFlfde.SwHCTb")
                val element2 = doc.select(".hqAUc.k0Rg6d > span")

                val views = RemoteViews(context.packageName, R.layout.u_s_d_widget)
                views.setTextViewText(R.id.usd, element.text())
                views.setTextViewText(R.id.updated, element2.text())


                appWidgetManager.updateAppWidget(appWidgetId, views)

            } catch (e : IOException) {

            }
        }
    ).start()
}