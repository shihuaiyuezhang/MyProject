package com.wiserv.util.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Properties;



public class SFtpFileUtil {

    private Logger logger = LoggerFactory.getLogger(SFtpFileUtil.class);

    private static SFtpFileUtil instance = new SFtpFileUtil();

    public static SFtpFileUtil getInstance() {
        return instance;
    }

    private ChannelSftp connect(String hostName, int port, String userName, String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(userName, hostName, port);
            Session sshSession = jsch.getSession(userName, hostName, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshConfig.put("PreferredAuthentications", "publickey,keyboard-interactive,password");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            logger.info("Sftp session connect to host:" + hostName);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return sftp;
    }


    public byte[] downloadFile(String hostName, int port, String userName, String password, String remoteFileName) {
        ChannelSftp sftp = null;
        ByteArrayOutputStream out = null;
        try {
            sftp = connect(hostName, port, userName, password);
            if (!isDictionary(sftp, remoteFileName)) {
                String remoteFolder = this.genParentPath4FTP(remoteFileName);
                sftp.cd(remoteFolder);
                out = new ByteArrayOutputStream();
                sftp.get(new File(remoteFileName).getName(), out);
                return out.toByteArray();
            } else {
                throw new Exception("The file download" + remoteFileName + "does not exist!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            IOUtil.quietClose(out);
            if (sftp != null) {
                try {
                    sftp.getSession().disconnect();
                } catch (JSchException e) {
                    logger.error(e.getMessage(),e);
                }

            }
        }
        return null;
    }


    private boolean isDictionary(ChannelSftp sftp, String remotePath) {
        remotePath = this.formatPath4FTP(remotePath);
        try {
            sftp.cd(remotePath);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private String formatPath4FTP(String path) {
        String reg = "\\\\+|/+";
        String temp = path.trim().replaceAll(reg, "/");
        if (temp.length() > 1 && temp.endsWith("/")) {
            temp = temp.substring(0, temp.length() - 1);
        }
        return temp;
    }

    public String genParentPath4FTP(String path) {
        String pp = new File(path).getParent();
        if (pp == null)
            return null;
        else
            return formatPath4FTP(pp);
    }
}
