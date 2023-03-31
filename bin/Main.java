public class Main {
	public static void main(String[] args) {
		java.lang.System.out.println("Hello World!");
		java.util.Enumeration enumeration = gnu.io.CommPortIdentifier.getPortIdentifiers();
		while (enumeration.hasMoreElements()) {
			java.lang.Object object = enumeration.nextElement();
			if (object instanceof gnu.io.CommPortIdentifier) {
				gnu.io.CommPortIdentifier commPortIdentifier = (gnu.io.CommPortIdentifier) object;
				java.lang.String name = commPortIdentifier.getName();
				java.lang.System.out.println(name);
			}
		}
		gnu.io.CommPortIdentifier commPortIdentifier = null;
		try {
			commPortIdentifier = gnu.io.CommPortIdentifier.getPortIdentifier("COM3");
		} catch (gnu.io.NoSuchPortException noSuchPortException) {
			noSuchPortException.printStackTrace();
		}
		if (commPortIdentifier != null) {
			if (commPortIdentifier.isCurrentlyOwned()) {
				java.lang.System.out.println("Error: Port is currently in use");
			} else {
				gnu.io.CommPort commPort = null;
				try {
					 commPort = commPortIdentifier.open("COMthree", 2000);
				} catch (gnu.io.PortInUseException portInUseException) {
					portInUseException.printStackTrace();
				}
				if (commPort instanceof gnu.io.SerialPort) {
					try {
						gnu.io.SerialPort serialPort = (gnu.io.SerialPort) commPort;
						serialPort.setSerialPortParams(57600, gnu.io.SerialPort.DATABITS_8, gnu.io.SerialPort.STOPBITS_1, gnu.io.SerialPort.PARITY_NONE);
						java.io.InputStream inputStream = serialPort.getInputStream();
						byte[] buffer = new byte[1024];
						int len = -1;
						while ((len = inputStream.read(buffer)) > -1) {
							java.lang.String receivedResult = new java.lang.String(buffer, 0, len);
							if (!receivedResult.equals("")) {
								java.lang.System.out.print(receivedResult);
							}
						}
					} catch (gnu.io.UnsupportedCommOperationException unsupoortedCommOperationException) {
						unsupoortedCommOperationException.printStackTrace();
					} catch (java.io.IOException ioException) {
						ioException.printStackTrace();
					}
				} else {
					java.lang.System.out.println("Error: Only serial ports are handled by this example");
				}
			}
		}
	}
}
