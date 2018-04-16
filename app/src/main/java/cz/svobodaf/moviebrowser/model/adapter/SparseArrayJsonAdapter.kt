package cz.svobodaf.moviebrowser.model.adapter

import android.util.SparseArray
import com.squareup.moshi.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class SparseArrayJsonAdapter<T>(
        private val elementAdapter: JsonAdapter<T>) : JsonAdapter<SparseArray<T>>() {

    object Factory : JsonAdapter.Factory {
        override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
            if (annotations.isNotEmpty()) return null

            val rawType = Types.getRawType(type)
            if (rawType != SparseArray::class.java) return null

            val elementType = (type as ParameterizedType).actualTypeArguments[0]
            return SparseArrayJsonAdapter(moshi.adapter<Any>(elementType))
        }
    }

    override fun fromJson(reader: JsonReader): SparseArray<T> {
        val result = SparseArray<T>()

        reader.beginArray()
        while (reader.hasNext()) {
            val item = elementAdapter.fromJson(reader)
            result.append((item as ISparseArrayItem).id ,item)
        }
        reader.endArray()
        return result
    }

    override fun toJson(writer: JsonWriter, value: SparseArray<T>?) {
        checkNotNull(value, { "Adapter doesn't support null. Wrap with nullSafe()." }).apply {
            writer.beginArray()
            for (i in 0 until size()) {
                elementAdapter.toJson(writer, valueAt(i))
            }
            writer.endArray()
        }
    }
}