package eu.qwan.editrain.boundary.rest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.UnsupportedEncodingException;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class MockMvcJsonRequests {
    static MockHttpServletRequestBuilder jsonGet(String urlTemplate) {
        return get(urlTemplate).accept(MediaType.APPLICATION_JSON);
    }

    static MockHttpServletRequestBuilder jsonPost(String urlTemplate, String body) {
        return post(urlTemplate).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(body).with(csrf());
    }

    static MockHttpServletRequestBuilder jsonPut(String urlTemplate, String body) {
        return put(urlTemplate).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(body).with(csrf());
    }

    static String responseStringFrom(ResultActions resultActions) throws UnsupportedEncodingException {
        return resultActions.andReturn().getResponse().getContentAsString();
    }
}
