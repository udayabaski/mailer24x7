package com.nervytech.mailer24x7.mailgun;

import javax.ws.rs.core.MediaType;

public class AttachInfo {
	
	//Default media type is APPLICATION_OCTET_STREAM_TYPE
	private MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM_TYPE;
	private String fileName = null;
	public AttachInfo(MediaType mediaType, String fileName)
	{
		this.mediaType = mediaType;
		this.fileName = fileName;
	}
	
	public MediaType getMediaType()
	{
		return this.mediaType;
	}
	
	public String getFileName()
	{
		return this.fileName;
	}

}
