package br.ufpe.cin.if710.managers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.ufpe.cin.if710.managers.alarm.AlarmManagerActivity
import br.ufpe.cin.if710.managers.jobscheduler.JobSchedulerActivity
import br.ufpe.cin.if710.managers.location.LocationMapsActivity
import br.ufpe.cin.if710.managers.notification.NotificationManagerActivity
import br.ufpe.cin.if710.managers.phonesms.PhoneSmsActivity
import br.ufpe.cin.if710.managers.pkg.PackageManagerActivity
import br.ufpe.cin.if710.managers.power.PowerManagerActivity
import br.ufpe.cin.if710.managers.sensor.SensorManagerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            startActivity(Intent(applicationContext, NotificationManagerActivity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(applicationContext, AlarmManagerActivity::class.java))
        }
        btn3.setOnClickListener {
            startActivity(Intent(applicationContext, JobSchedulerActivity::class.java))
        }
        btn4.setOnClickListener {
            startActivity(Intent(applicationContext, PowerManagerActivity::class.java))
        }
        btn5.setOnClickListener {
            startActivity(Intent(applicationContext, SensorManagerActivity::class.java))
        }
        btn6.setOnClickListener {
            startActivity(Intent(applicationContext, PackageManagerActivity::class.java))
        }
        btn7.setOnClickListener {
            startActivity(Intent(applicationContext, PhoneSmsActivity::class.java))
        }
        btn8.setOnClickListener {
            startActivity(Intent(applicationContext, LocationMapsActivity::class.java))
        }
    }

    companion object {

        val PACKAGE_NAME = "br.ufpe.cin.if710.managers"
    }
}