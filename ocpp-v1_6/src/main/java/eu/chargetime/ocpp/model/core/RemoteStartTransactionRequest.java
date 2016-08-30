package eu.chargetime.ocpp.model.core;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;

/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Sent to Charge Point by Central System.
 */
public class RemoteStartTransactionRequest implements Request {

    private Integer connectorId;
    private IdToken idTag;
    private ChargingProfile chargingProfile;

    @Override
    public boolean validate() {
        boolean valid = true;
        if (valid &= idTag != null)
            valid &= idTag.validate();

        if (chargingProfile != null) {
            valid &= chargingProfile.validate();
            valid &= "TxProfile".equals(chargingProfile.getChargingProfilePurpose());
        }
        return valid;
    }

    /**
     * Number of the connector on which to start the transaction.
     * connectorId SHALL be &gt; 0.
     *
     * @return Connector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Optional. Number of the connector on which to start the transaction.
     * connectorId SHALL be &gt; 0.
     *
     * @param connectorId integer, connector
     * @throws PropertyConstraintException Value is 0 or negative.
     */
    public void setConnectorId(Integer connectorId) throws PropertyConstraintException {
        if (connectorId <= 0)
            throw new PropertyConstraintException("connectorId", connectorId);

        this.connectorId = connectorId;
    }

    /**
     * The identifier that Charge Point must use to start a transaction.
     *
     * @return an {@link IdToken}.
     */
    public IdToken getIdTag() {
        return idTag;
    }

    /**
     * Required. The identifier that Charge Point must use to start a transaction.
     *
     * @param idTag    an {@link IdToken}.
     */
    public void setIdTag(IdToken idTag) {
        this.idTag = idTag;
    }

    /**
     * Charging Profile to be used by the Charge Point for the requested transaction.
     *
     * @return the {@link ChargingProfile}.
     */
    public ChargingProfile getChargingProfile() {
        return chargingProfile;
    }

    /**
     * Optional. Charging Profile to be used by the Charge Point for the requested transaction.
     * {@link ChargingProfile#setChargingProfilePurpose(ChargingProfilePurposeType)} MUST be set to TxProfile.
     *
     * @param chargingProfile   the {@link ChargingProfile}.
     */
    public void setChargingProfile(ChargingProfile chargingProfile) {
        this.chargingProfile = chargingProfile;
    }
}