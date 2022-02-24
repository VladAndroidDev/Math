package com.v.nevi.p.sv.android.math

import android.content.Context
import java.io.*

class SerializableManager {

    companion object {
        fun <T : Serializable?> saveSerializable(
            context: Context,
            objectToSave: T,
            fileName: String?
        ) {
            try {
                val fileOutputStream: FileOutputStream =
                    context.openFileOutput(fileName, Context.MODE_PRIVATE)
                val objectOutputStream = ObjectOutputStream(fileOutputStream)
                objectOutputStream.writeObject(objectToSave)
                objectOutputStream.close()
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun <T : Serializable?> readSerializable(context: Context, fileName: String?): T? {
            var objectToReturn: T? = null
            try {
                val fileInputStream: FileInputStream = context.openFileInput(fileName)
                val objectInputStream = ObjectInputStream(fileInputStream)
                objectToReturn = objectInputStream.readObject() as T?
                objectInputStream.close()
                fileInputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: ClassCastException) {
                e.printStackTrace()
            }
            return objectToReturn
        }

        /**
         * Removes a specified file.
         *
         * @param context The application context.
         * @param filename The name of the file.
         */
        fun removeSerializable(context: Context, filename: String?) {
            context.deleteFile(filename)
        }
    }
}