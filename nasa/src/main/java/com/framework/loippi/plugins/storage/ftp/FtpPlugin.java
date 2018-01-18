package com.framework.loippi.plugins.storage.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;

import com.framework.loippi.entity.PluginConfig;
import com.framework.loippi.plugins.storage.StoragePlugin;
import com.framework.loippi.support.FileInfo;

/**
 * Plugin - FTP
 * 
 * @author Mounate Yan。
 * @version 1.0
 */
@Component("ftpPlugin")
public class FtpPlugin extends StoragePlugin {
	
	public static String FTP_HOST = "host";
	public static String FTP_PORT = "port";
	public static String FTP_URL_PREFIX = "urlPrefix";
	public static String FTP_USERNAME = "username";
	public static String FTP_PASSWORD = "password";

	@Override
	public String getName() {
		return "FTP存储";
	}

	@Override
	public String getVersion() {
		return "2.0";
	}

	@Override
	public String getAuthor() {
		return "Loippi Team";
	}

	@Override
	public String getSiteUrl() {
		return "http://www.loippi.com";
	}



	@Override
	public String getInstallUrl() {
		return "ftp/install.jhtml";
	}

	@Override
	public String getUninstallUrl() {
		return "ftp/uninstall.jhtml";
	}

	@Override
	public String getSettingUrl() {
		return "ftp/setting.jhtml";
	}

	@Override
	public void upload(String path, File file, String contentType) {
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String host = pluginConfig.getAttribute(FTP_HOST);
			Integer port = Integer.valueOf(pluginConfig.getAttribute(FTP_PORT));
			String username = pluginConfig.getAttribute(FTP_USERNAME);
			String password = pluginConfig.getAttribute(FTP_PASSWORD);
			FTPClient ftpClient = new FTPClient();
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(file);
				ftpClient.connect(host, port);
				ftpClient.login(username, password);
				ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
					String directory = StringUtils.substringBeforeLast(path, "/");
					String filename = StringUtils.substringAfterLast(path, "/");
					if (!ftpClient.changeWorkingDirectory(directory)) {
						String[] paths = StringUtils.split(directory, "/");
						String p = "/";
						ftpClient.changeWorkingDirectory(p);
						for (String s : paths) {
							p += s + "/";
							if (!ftpClient.changeWorkingDirectory(p)) {
								ftpClient.makeDirectory(s);
								ftpClient.changeWorkingDirectory(p);
							}
						}
					}
					ftpClient.storeFile(filename, inputStream);
					ftpClient.logout();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(inputStream);
				if (ftpClient.isConnected()) {
					try {
						ftpClient.disconnect();
					} catch (IOException e) {
					}
				}
			}
		}
	}

	@Override
	public String getUrl(String path) {
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String urlPrefix = pluginConfig.getAttribute(FTP_URL_PREFIX);
			return urlPrefix + path;
		}
		return null;
	}

	@Override
	public List<FileInfo> browser(String path) {
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String host = pluginConfig.getAttribute(FTP_HOST);
			Integer port = Integer.valueOf(pluginConfig.getAttribute(FTP_PORT));
			String username = pluginConfig.getAttribute(FTP_USERNAME);
			String password = pluginConfig.getAttribute(FTP_PASSWORD);
			String urlPrefix = pluginConfig.getAttribute(FTP_URL_PREFIX);
			FTPClient ftpClient = new FTPClient();
			try {
				ftpClient.connect(host, port);
				ftpClient.login(username, password);
				ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode()) && ftpClient.changeWorkingDirectory(path)) {
					for (FTPFile ftpFile : ftpClient.listFiles()) {
						FileInfo fileInfo = new FileInfo();
						fileInfo.setName(ftpFile.getName());
						fileInfo.setUrl(urlPrefix + path + ftpFile.getName());
						fileInfo.setIsDirectory(ftpFile.isDirectory());
						fileInfo.setSize(ftpFile.getSize());
						fileInfo.setLastModified(ftpFile.getTimestamp().getTime());
						fileInfos.add(fileInfo);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (ftpClient.isConnected()) {
					try {
						ftpClient.disconnect();
					} catch (IOException e) {
					}
				}
			}
		}
		return fileInfos;
	}

}