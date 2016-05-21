package com.itexpertnepal.simpleinvoice.api;

/**
 *
 * @author binay
 */
public interface UploadManager {

    public void bulkCustomerUpload(String data, String userName);

    public void bulkProductUpload(String data, String userName);

}
