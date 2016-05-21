package com.itexpertnepal.simpleinvoice.ui.controller;

import com.itexpertnepal.simpleinvoice.domain.CustomerService.FrequencyType;
import com.itexpertnepal.simpleinvoice.domain.Payment;
import com.itexpertnepal.simpleinvoice.domain.Payment.PaymentType;
import com.itexpertnepal.simpleinvoice.domain.Product;
import com.itexpertnepal.simpleinvoice.domain.Product.InvoiceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Scope("singleton")
@Component
public class HelperUtility implements Serializable {

    public SelectItem[] getFrequencyType() {
        FrequencyType[] types = FrequencyType.values();
        List<FrequencyType> list = new ArrayList<FrequencyType>();
        for (FrequencyType f : types) {
            if (!f.equals(FrequencyType.General)) {
                list.add(f);
            }
        }
        return toArrayOfSelectItem(list);
    }

    public SelectItem[] getInvoiceType() {
        InvoiceType[] types = InvoiceType.values();
        List<InvoiceType> list = new ArrayList<InvoiceType>();
        for (InvoiceType i : types) {
            list.add(i);
        }
        return toArrayOfSelectItem(list);
    }

    public SelectItem[] getPaymentType() {
        PaymentType[] types = PaymentType.values();
        List<PaymentType> list = new ArrayList<PaymentType>();
        for (PaymentType i : types) {
            list.add(i);
        }
        return toArrayOfSelectItem(list);
    }

    public <T> SelectItem[] toArrayOfSelectItem(List<T> obj) {
        SelectItem[] items = new SelectItem[obj.size()];
        int index = 0;
        for (T r : obj) {
            items[index] = new SelectItem(r);
            index++;
        }
        return items;
    }
}
