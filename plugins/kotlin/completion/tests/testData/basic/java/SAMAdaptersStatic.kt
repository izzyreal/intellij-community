fun foo() {
    javax.swing.SwingUtilities.invoke<caret>
}

// EXIST: { lookupString: "invokeLater", itemText: "invokeLater", tailText: "(doRun: Runnable!)", typeText: "Unit", icon: "fileTypes/javaClass.svg"}
// EXIST: { lookupString: "invokeLater", itemText: "invokeLater", tailText: " {...} (doRun: (() -> Unit)!)", typeText: "Unit", icon: "fileTypes/javaClass.svg"}
