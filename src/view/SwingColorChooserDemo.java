package view;
/* From http://java.sun.com/docs/books/tutorial/index.html */

/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* SwingColorChooserDemo.java is a 1.4 application that requires no other files. */
public class SwingColorChooserDemo extends JPanel implements ChangeListener {

  protected JColorChooser tcc;
  protected JLabel banner;

  public SwingColorChooserDemo() {
    super(new BorderLayout());

    //Set up the banner at the top of the window
    banner = new JLabel("Color Chooser", JLabel.CENTER);
    banner.setForeground(Color.black);
    banner.setBackground(Color.blue);
    banner.setOpaque(true);
    banner.setFont(new Font("SansSerif", Font.BOLD, 24));
    banner.setPreferredSize(new Dimension(100, 65));

    JPanel bannerPanel = new JPanel(new BorderLayout());
    bannerPanel.add(banner, BorderLayout.CENTER);
    bannerPanel.setBorder(BorderFactory.createTitledBorder("Banner"));

    //Set up color chooser for setting text color
    tcc = new JColorChooser(banner.getForeground());
    tcc.getSelectionModel().addChangeListener(this);
    tcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));

    add(bannerPanel, BorderLayout.CENTER);
    add(tcc, BorderLayout.PAGE_END);
  }

  public void stateChanged(ChangeEvent e) {
    Color newColor = tcc.getColor();
    Window.canvas.setColor(newColor);
    banner.setForeground(newColor);
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  static void createAndShowGUI() {
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    JFrame frame = new JFrame("SwingColorChooserDemo");

    //Create and set up the content pane.
    JComponent newContentPane = new SwingColorChooserDemo();
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

}