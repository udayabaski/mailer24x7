/**
 * 
 */
package com.nervytech.mailer24x7.aws.s3.client;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.StorageClass;
import com.nervytech.mailer24x7.client.exception.MailerS3Exception;

/**
 * @author bsikkaya
 * 
 */ 
public class MailerS3Client {

	private static final Logger logger = LoggerFactory
			.getLogger(MailerS3Client.class);

	/*
	 * private static final Logger logger = LoggerFactory
	 * .getLogger(MailerS3Client.class);
	 */
	public static String BUCKET_NAME = "mailer24x7";
	public static String S3_URL = "https://s3-ap-southeast-1.amazonaws.com/"
			+ BUCKET_NAME;
	public static String DEFAULT_HTML_FILENAME = "Campaign.html";
	public static String DEFAULT_TEXT_FILENAME = "Campaign.txt";

	final static int BUFFER = 1024;

	public static AmazonS3 s3Client = null;

	/**
	 * The only information needed to create a client are security credentials
	 * consisting of the AWS Access Key ID and Secret Access Key. All other
	 * configuration, such as the service endpoints, are performed
	 * automatically. Client parameters, such as proxies, can be specified in an
	 * optional ClientConfiguration object when constructing a client.
	 * 
	 * @see com.amazonaws.auth.BasicAWSCredentials
	 * @see com.amazonaws.auth.PropertiesCredentials
	 * @see com.amazonaws.ClientConfiguration
	 */
	private static void init() throws Exception {
		try {
			AWSCredentials credentials = new PropertiesCredentials(
					MailerS3Client.class
							.getResourceAsStream("/resources/AwsCredentials.properties"));

			s3Client = new AmazonS3Client(credentials);
			// Setting to Singapore Endpoint ...
			// s3Client.setEndpoint(S3_ENDPOINT);
		} catch (Exception e) {
			logger.error("Unable to read AWS property file ", e);
			throw new MailerS3Exception("Unable to read AWS property file ", e);
		}
	}

	public static AmazonS3 getAmazonS3Client() {
		if (s3Client == null) {
			try {
				init();
			} catch (Exception e) {
				logger.error("Error while getting S3 conntection");
			}
			return s3Client;
		} else {
			return s3Client;
		}
	}

	public static String putTxtObject(long orgId, long userId, long campaignId,
			String htmlContent, boolean isPublic) {
		return putObject(orgId, userId, campaignId, new ByteArrayInputStream(
				htmlContent.getBytes()), DEFAULT_TEXT_FILENAME, isPublic);
	}

	public static String putHtmlObject(long orgId, long userId,
			long campaignId, String htmlContent, boolean isPublic) {
		return putObject(orgId, userId, campaignId, new ByteArrayInputStream(
				htmlContent.getBytes()), DEFAULT_HTML_FILENAME, isPublic);
	}

	public static String putObject(long orgId, long userId, long campaignId,
			File file, String fileName, boolean isPublic) {

		String key = orgId + "/" + userId + "/" + campaignId + "/" + fileName;
		PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, key, file);
		request.setStorageClass(StorageClass.ReducedRedundancy);

		if (isPublic) {
			AccessControlList aclList = new AccessControlList();
			aclList.grantPermission(GroupGrantee.AllUsers, Permission.Read);
			/*
			 * Set<Grant> grantsSet = aclList.getGrants();
			 * 
			 * Grant grant = new Grant(GroupGrantee.AuthenticatedUsers,
			 * permission); grantsSet.add();
			 */
			request.setAccessControlList(aclList);
		}

		PutObjectResult result = getAmazonS3Client().putObject(request);
		// result.getETag();

		return key;
	}

	public static String putObject(long orgId, long userId, long campaignId,
			InputStream in, String fileName, boolean isPublic) {
		String key = orgId + "/" + userId + "/" + campaignId + "/" + fileName;
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setHeader("fileName", fileName);
		PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, key, in,
				metaData);
		request.setStorageClass(StorageClass.ReducedRedundancy);

		if (isPublic) {
			AccessControlList aclList = new AccessControlList();
			aclList.grantPermission(GroupGrantee.AllUsers, Permission.Read);
			/*
			 * Set<Grant> grantsSet = aclList.getGrants();
			 * 
			 * Grant grant = new Grant(GroupGrantee.AuthenticatedUsers,
			 * permission); grantsSet.add();
			 */
			request.setAccessControlList(aclList);
		}

		PutObjectResult result = getAmazonS3Client().putObject(request);
		// result.getETag();

		return key;
	}

	public static void deleteObject(String key) {
		DeleteObjectRequest request = null;

		try {
			request = new DeleteObjectRequest(BUCKET_NAME, key);
			
			getAmazonS3Client().deleteObject(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getTextObject(String key) {

		String textContent = null;
		GetObjectRequest request = null;
		S3Object s3Object = null;
		S3ObjectInputStream s3InputStream = null;

		try {
			request = new GetObjectRequest(BUCKET_NAME, key);
			s3Object = getAmazonS3Client().getObject(request);
			s3InputStream = s3Object.getObjectContent();
			textContent = new Scanner(s3InputStream).useDelimiter("\\A").next();
		} finally {
			if (s3InputStream != null) {
				try {
					s3InputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return textContent;

	}

	public static String getHTMLObject(String key) {

		String htmlContent = null;
		GetObjectRequest request = null;
		S3Object s3Object = null;
		S3ObjectInputStream s3InputStream = null;

		try {
			request = new GetObjectRequest(BUCKET_NAME, key);
			s3Object = getAmazonS3Client().getObject(request);
			s3InputStream = s3Object.getObjectContent();
			htmlContent = new Scanner(s3InputStream).useDelimiter("\\A").next();
		} finally {
			if (s3InputStream != null) {
				try {
					s3InputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return htmlContent;

	}

	public static String processZipFile(CommonsMultipartFile file, long orgId,
			long userId, long campaignId) throws IOException {
		InputStream in = null;
		ZipInputStream zIn = null;
		String s3Path = null;
		File tempFile = null;

		try {
			zIn = new ZipInputStream(new BufferedInputStream(
					file.getInputStream()));

			ZipEntry zipEntry;

			while ((zipEntry = zIn.getNextEntry()) != null) {

				String fileName = zipEntry.getName().toLowerCase();

				System.out.println("FileName is " + fileName);

				String randomId = UUID.randomUUID().toString();

				tempFile = new File(randomId + "###" + fileName);

				IOUtils.copy(zIn, new FileOutputStream(tempFile));

				if (fileName.lastIndexOf(".css") != -1) {
					// putObject(orgId, userId, campaignId, zIn, fileName,
					// true);
					putObject(orgId, userId, campaignId, tempFile, fileName,
							true);
				} else if (fileName.lastIndexOf(".jpg") != -1
						|| fileName.lastIndexOf(".gif") != -1
						|| fileName.lastIndexOf(".png") != -1) {
					// putObject(orgId, userId, campaignId, zIn, fileName,
					// true);
					putObject(orgId, userId, campaignId, tempFile, fileName,
							true);
				} else if (fileName.lastIndexOf(".html") != -1) {
					// putObject(orgId, userId, campaignId, zIn, fileName);

					/*
					 * int size; byte[] buffer = new byte[1024]; StringBuilder
					 * builder = new StringBuilder();
					 * 
					 * while ((size = zIn.read(buffer, 0, buffer.length)) != -1)
					 * { builder.append(new String(buffer)); }
					 * 
					 * String parsedString = HTMLParser.replaceWithS3Url(
					 * builder.toString(), "href=\"" + s3Url + "/" + orgId + "/"
					 * + "/" + userId + "/" + campaignId + "/");
					 */
					// s3Path = putObject(orgId, userId, campaignId, zIn,
					// fileName,true);
					s3Path = putObject(orgId, userId, campaignId, tempFile,
							fileName, true);
				}

				tempFile.delete();
				zIn.closeEntry();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zIn != null) {
				zIn.close();
			}
		}

		return s3Path;
	}

	public static void main(String args[]) {
		// putObject(2l, 2l, 3l, new
		// ByteArrayInputStream("Samiksha".getBytes()));
	}

}
