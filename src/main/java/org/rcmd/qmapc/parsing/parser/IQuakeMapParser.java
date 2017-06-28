/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

/**
 * Interface for Quake map parsers.
 *
 * @author spirit
 */
public interface IQuakeMapParser {

    /**
     * Handles parsing a map that is to follow in the input token sequence.
     */
    public void map();

    /**
     * Handles parsing a brush that is to follow in the input token sequence.
     */
    public void brush();

    /**
     * Handles parsing an entity that is to follow in the input token sequence.
     */
    public void entity();

}
