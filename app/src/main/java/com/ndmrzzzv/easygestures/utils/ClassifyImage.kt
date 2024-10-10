package com.ndmrzzzv.easygestures.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.ndmrzzzv.easygestures.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ClassifyImage(
    private val context: Context
) {
    private val imageSize = 224

    fun classifyImage(image: Bitmap, onResult: (String) -> Unit = {}) {
        try {
            val resizedImage = Bitmap.createScaledBitmap(image, imageSize, imageSize, false)

            val model = Model.newInstance(context)

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, imageSize, imageSize, 3), DataType.FLOAT32)
            val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
            byteBuffer.order(ByteOrder.nativeOrder())

            val intValues = IntArray(imageSize * imageSize)
            resizedImage.getPixels(intValues, 0, resizedImage.width, 0, 0, resizedImage.width, resizedImage.height)

            var pixel = 0
            for (i in 0 until imageSize) {
                for (j in 0 until imageSize) {
                    val `val` = intValues[pixel++] // RGB
                    byteBuffer.putFloat((`val` shr 16 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((`val` shr 8 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((`val` and 0xFF) * (1f / 255f))
                }
            }

            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val confidences = outputFeature0.floatArray
            var maxPos = 0
            var maxConfidence = 0f
            for (i in confidences.indices) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i]
                    maxPos = i
                }
            }
            val classes = arrayOf(
                "A letter", "B letter", "C letter", "D letter", "E letter",
                "F letter", "G letter", "H letter", "I letter", "K letter"
            )

            val result = classes[maxPos].split(" ")[0]
            model.close()

            onResult(result)

        } catch (exception: Exception) {
            Log.d("ClassifyImage", exception.message.toString())
            onResult("")
        }
    }
}