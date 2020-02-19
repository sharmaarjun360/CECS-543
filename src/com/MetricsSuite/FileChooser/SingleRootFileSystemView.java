package com.MetricsSuite.FileChooser;

import java.io.*;
import javax.swing.filechooser.*;
//Reference: https://tips4java.wordpress.com/2009/01/28/single-root-file-chooser/

public class SingleRootFileSystemView extends FileSystemView
{
    File root;
    File[] roots = new File[1];

    public SingleRootFileSystemView(File root)
    {
        super();
        this.root = root;
        roots[0] = root;
    }

    @Override
    public File createNewFolder(File containingDir)
    {
        File folder = new File(containingDir, "New Folder");
        folder.mkdir();
        return folder;
    }

    @Override
    public File getDefaultDirectory()
    {
        return root;
    }

    @Override
    public File getHomeDirectory()
    {
        return root;
    }

    @Override
    public File[] getRoots()
    {
        return roots;
    }
}
