/*
 * This file is part of qmapc. See the LICENSE file for license information.
 */
package org.rcmd.qmapc.parsing.parser;

import org.rcmd.qmapc.ir.parsetree.ParseTree;

/**
 * An interface for parsers which generate a parse tree while parsing a file.
 *
 * @author spirit
 */
public interface IParseTreeTrackingParser {

    /**
     * Gets the root of the constructed parse tree. Use this to walk the tree.
     *
     * @return the parse tree root
     */
    public ParseTree getRoot();

    /**
     * Gets the current node which is under construction and will be added to
     * the parse tree once it is done.
     *
     * @return the current parse tree node
     */
    public ParseTree getCurrentNode();

}
