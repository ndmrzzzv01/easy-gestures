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

    fun classifyImage(image: Bitmap) {
        try {
            val model = Model.newInstance(context)

            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
            byteBuffer.order(ByteOrder.nativeOrder())

            val intValues = IntArray(imageSize * imageSize)
            image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)

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
                "A letter",
                "B letter",
                "C letter",
                "D letter",
                "E letter",
                "F letter",
                "G letter",
                "H letter",
                "I letter",
                "K letter"
            )
            Log.d("TESTV", classes[maxPos])
//            binding.result.text = classes[maxPos]

            var s: String? = ""
            for (i in classes.indices) {
                s += java.lang.String.format("%s: %.1f%%\n", classes[i], confidences[i] * 100)
            }
            Log.d("TESTV - 2", s.toString())
//            binding.confidence.text = s

            model.close()
        } catch (exception: Exception) {

        }
    }

}