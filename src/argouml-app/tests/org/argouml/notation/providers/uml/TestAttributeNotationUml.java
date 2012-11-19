/* $Id$
 *****************************************************************************
 * Copyright (c) 2009-2011 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Tom Morris
 *    Michiel van der Wulp
 *****************************************************************************
 *
 * Some portions of this file was previously release using the BSD License:
 */

// Copyright (c) 2006-2008 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies. This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason. IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.notation.providers.uml;

import junit.framework.TestCase;
import org.argouml.kernel.Project;
import org.argouml.kernel.ProjectManager;
import org.argouml.model.InitializeModel;
import org.argouml.model.Model;
import org.argouml.notation.InitNotation;
import org.argouml.notation.NotationProvider;
import org.argouml.notation.NotationSettings;
import org.argouml.notation.providers.java.InitNotationJava;
import org.argouml.profile.ProfileFacade;
import org.argouml.profile.init.InitProfileSubsystem;

import java.text.ParseException;

/**
 * @author michiel
 */
public class TestAttributeNotationUml extends TestCase {

    private Object model;
    private Project project;
    private Object attrType;
    private Object attr;
    private NotationProvider np;

    /**
     * The constructor.
     *
     * @param str the name of the test
     */
    public TestAttributeNotationUml(String str) {
        super(str);
        InitializeModel.initializeDefault();
    }

    /*
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws ParseException {
        (new InitProfileSubsystem()).init();
        (new InitNotation()).init();
        (new InitNotationUml()).init();
        (new InitNotationJava()).init();
        project = ProjectManager.getManager().makeEmptyProject();
        ProjectManager.getManager().setCurrentProject(project);
        model = project.getUserDefinedModelList().iterator().next();


        attrType = project.getDefaultAttributeType();
        attr = Model.getCoreFactory().buildAttribute2(attrType);


        np = null;
        if (Model.getFacade().isAAttribute(attr)) {
            AttributeNotationUml anu = new AttributeNotationUml(attr);
            anu.parseAttribute("name", attr);
            np = anu;
        } else if (Model.getFacade().isAOperation(attr)) {
            OperationNotationUml onu = new OperationNotationUml(attr);
            onu.parseOperation("name", attr);
            np = onu;
        } else {
            fail("Unknown feature type " + attr);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        ProjectManager.getManager().removeProject(
                ProjectManager.getManager().getCurrentProject());
        ProfileFacade.reset();
        super.tearDown();
    }

    public void testDefaultNotationSettings() throws ParseException {
        String str = np.toString(attr, NotationSettings.getDefaultSettings());
        assertEquals("name : Integer", str);
    }

    public void testVisibility() throws ParseException {
        NotationSettings notationSettings = NotationSettings.getDefaultSettings();

        notationSettings.setShowVisibilities(true);

        String str = np.toString(attr, notationSettings);
        assertEquals("+name : Integer", str);
    }


    public void testInitialValues() throws ParseException {
        NotationSettings notationSettings = NotationSettings.getDefaultSettings();

        notationSettings.setShowVisibilities(true);

        String str = np.toString(attr, notationSettings);
        assertEquals("+name : Integer", str);
    }


    public void testShowProperties() throws ParseException {
        NotationSettings notationSettings = NotationSettings.getDefaultSettings();

        notationSettings.setShowVisibilities(true);

        String str = np.toString(attr, notationSettings);
        assertEquals("+name : Integer", str);
    }

    public void testTV() throws ParseException {


        NotationSettings notationSettings = NotationSettings.getDefaultSettings();

        notationSettings.setShowVisibilities(true);

        String str = np.toString(attr, notationSettings);
        assertEquals("+name : Integer", str);
    }


}
