package com.cnet.payment.provider.utils;

import java.util.Arrays;
import java.util.List;

import com.cnet.payment.provider.bean.ArgumentBean;

/**
 * @author TAREQ SAAD
 *
 */
public class ApplicationUtility {
	
	/**
	 * @param argumentArray
	 * @return
	 */
	public ArgumentBean extractArguments(String[] argumentArray) {
		
		ArgumentBean argumentBean = new ArgumentBean();
		List<String> arguments = Arrays.asList(argumentArray);
		for (String argument : arguments) {
			if (argument.startsWith("clientId")) {
				argumentBean.setClientId( argument.replace("\"", "").replace("clientId=", ""));
			} else if (argument.startsWith("orderId")) {
				argumentBean.setOrderId( argument.replace("\"", "").replace("orderId=", ""));	
			}
			if (argument.startsWith("amount")) {
				argumentBean.setAmount( argument.replace("\"", "").replace("amount=", ""));
			} else if (argument.startsWith("currency")) {
				argumentBean.setCurrencyCode(argument.replace("\"", "").replace("currency=", ""));
			} else if (argument.startsWith("payMethod")) {
				argumentBean.setPaymentMethod( argument.replace("\"", "").replace("payMethod=", ""));
			} else if (argument.startsWith("payTokenId")) {
				argumentBean.setPayTokenId(argument.replace("\"", "").replace("payTokenId=", ""));
			}
		}
		
		return argumentBean;
				
		
		
		
	}

}
