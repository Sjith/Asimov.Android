package com.teracode.android.common.http.post;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;

import com.teracode.android.common.exception.code.CommonErrorCode;

/**
 * @author Maxi Rosson
 */
public class MultipartEntityBuilder {

	private final MultipartEntity multipartEntity = new MultipartEntity();

	/**
	 * @param name
	 * @param value
	 */
	public void addParameter(String name, Long value) {
		addParameter(name, value.toString());
	}

	/**
	 * @param name
	 * @param value
	 */
	public void addParameter(String name, Integer value) {
		addParameter(name, value.toString());
	}

	/**
	 * @param name
	 * @param value
	 */
	public void addParameter(String name, String value) {
		try {
			multipartEntity.addPart(name, new StringBody(value));
		} catch (UnsupportedEncodingException e) {
			throw CommonErrorCode.INTERNAL_ERROR.newProgramException(e);
		}
	}

	/**
	 * @param name
	 * @param in
	 * @param mimeType
	 * @param filename
	 */
	public void addParameter(String name, ByteArrayInputStream in, String mimeType, String filename) {
		multipartEntity.addPart(name, new ByteArrayInputStreamBody(in, mimeType, filename));
	}

	/**
	 * @return the multipartEntity
	 */
	public MultipartEntity getMultipartEntity() {
		return multipartEntity;
	}

}
