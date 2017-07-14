package com.zeng.fanda.mylibrary.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by David on 2017/6/6.
 */

public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[0];

    private Type type;
    private ParserConfig parserConfig;
    private int featureValues;
    private Feature[] features;

    public FastJsonResponseBodyConverter(Type type, ParserConfig parserConfig, int featureValues, Feature[] features) {
        this.type = type;
        this.parserConfig = parserConfig;
        this.featureValues = featureValues;
        this.features = features;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            return JSON.parseObject(value.string(), type, parserConfig, featureValues, features != null ? features : EMPTY_SERIALIZER_FEATURES);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            value.close();
        }
    }

}
