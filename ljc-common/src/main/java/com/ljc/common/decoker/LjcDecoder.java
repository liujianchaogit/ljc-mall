package com.ljc.common.decoker;

import com.ljc.common.api.R;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.lang.reflect.Type;

public class LjcDecoder implements Decoder {

    private final OptionalDecoder optionalDecoder;

    public LjcDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.optionalDecoder = new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters)));
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        Object o;
        try {
            o = optionalDecoder.decode(response, ParameterizedTypeImpl.make(R.class, new Type[]{type}, null));
        } catch (Exception e) {
            o = optionalDecoder.decode(response, type);
        }
        if (o instanceof R) {
            if (((R<?>) o).isSuccess())
                return ((R<?>) o).getData();
            throw new RuntimeException(((R<?>) o).getErrorMessage());
        }
        return o;
    }

}
