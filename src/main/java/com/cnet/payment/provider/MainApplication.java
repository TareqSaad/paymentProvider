package com.cnet.payment.provider;

import org.apache.log4j.Logger;

import com.cnet.payment.provider.constant.ErrorConstants;
import com.cnet.payment.provider.constant.PaymentProviderConstants;
import com.cnet.payment.provider.controller.PaymentProviderController;

/**
 * 
 * @author TAREQ SAAD
 * 
 *         The Main Class for payment provider application
 *
 */
public class MainApplication {

	private static final Logger logger = Logger.getLogger(MainApplication.class);


	public static void main(String[] args) {

		

		if (args.length > 0) {
			
			try {
			
			PaymentProviderController paymentProvider = new PaymentProviderController();
			paymentProvider.processArguments(args);
			System.exit(0);
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				System.exit(0);
			}

		}

		else {
			logger.error(ErrorConstants.VALID_COMMAND);
			System.exit(0);
		}

	}

	

}
