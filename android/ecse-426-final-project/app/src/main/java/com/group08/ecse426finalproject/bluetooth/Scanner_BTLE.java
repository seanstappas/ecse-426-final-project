package com.group08.ecse426finalproject.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.content.Context;
import android.os.Handler;

import com.group08.ecse426finalproject.utils.BluetoothUtils;
import com.group08.ecse426finalproject.utils.ToastShower;

@TargetApi(21)

public class Scanner_BTLE {

    private BluetoothActivity mBluetoothActivity;

    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;

    BluetoothLeScanner mLeScanner;
    private ScanCallback mScanCallback;



    private long scanPeriod;
    private int signalStrength;

    public Scanner_BTLE(BluetoothActivity bluetoothActivity, long scanPeriod, int signalStrength) {
        mBluetoothActivity = bluetoothActivity;

        mHandler = new Handler();

        this.scanPeriod = scanPeriod;
        this.signalStrength = signalStrength;

        // fetches bluetooth adapter with the bluetooth manager

        final BluetoothManager bluetoothManager =
                (BluetoothManager) bluetoothActivity.getSystemService(Context.BLUETOOTH_SERVICE);

        mBluetoothAdapter = bluetoothManager.getAdapter();
    }

    public boolean isScanning(){
        return mScanning;
    }
    public void start(){
        if (!BluetoothUtils.checkBluetooth(mBluetoothAdapter)) {
            BluetoothUtils.requestUserBluetooth(mBluetoothActivity);
            mBluetoothActivity.stopScan();
        }
        else {
            scanLeDevice(true);
        }
    }

    public void stop(){
        scanLeDevice(false);
    }

    private void scanLeDevice(final boolean enable){

        if(enable && !mScanning) {
            ToastShower.showToast(mBluetoothActivity.getApplicationContext(), "Started BLE scanning...");

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ToastShower.showToast(mBluetoothActivity.getApplicationContext(), "Stopping BLE Scanning...");

                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);

                    mBluetoothActivity.stopScan();
                }
            }, scanPeriod);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
            /**
             * If you want to scan for only specific types of peripherals,
             * you can instead call startLeScan(UUID[], BluetoothAdapter.LeScanCallback),
             * providing an array of UUID objects that
             * specify the GATT services your app supports.
             * source: https://developer.android.com/guide/topics/connectivity/bluetooth-le.html
             */
//            mBluetoothAdapter.startLeScan(new UUID[], mLeScanCallback);

            //api 18-20
//            if (Build.VERSION.SDK_INT < 21) {
//                mBluetoothAdapter.startLeScan(mLeScanCallback);
//            } else {
//                //api 21
//                // request BluetoothLeScanner if it hasn't been initialized yet
//                if (mLeScanner == null) mLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
//                // start scan in low latency mode
//                mLeScanner.startScan(new ArrayList<ScanFilter>(),
//                        new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build(), mScanCallback);
//            }
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }


    // adding devices to list...
    // scan call back, result

    private  BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            final int new_rssi = rssi; // rssi == signal strength
            if(rssi > signalStrength){
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mBluetoothActivity.addDevice(device, new_rssi);
                    }
                });
            }
        }
    };
    public BluetoothAdapter getBluetoothAdapter(){
        return this.mBluetoothAdapter;
    }
}
