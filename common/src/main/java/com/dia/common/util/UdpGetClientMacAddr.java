package com.dia.common.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class UdpGetClientMacAddr {

	private String sRemoteAddr;

	private int iRemotePort = 137;

	private byte[] buffer = new byte[1024];

	private DatagramSocket ds = null;

	/**
	 * 
	 * 利用远端IP地址创建构造函数
	 * 
	 * @param strAddr
	 * 
	 * @throws Exception
	 * 
	 */

	public UdpGetClientMacAddr(String strAddr) throws Exception

	{

		sRemoteAddr = strAddr;

		ds = new DatagramSocket();

	}

	/**
	 * 
	 * 获取远端IP地址对应的MAC地址信息
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */

	public final String GetRemoteMacAddr() throws Exception

	{

		byte[] bqcmd = GetQueryCmd();

		// 发送查询MAC地址命令

		send(bqcmd);

		DatagramPacket dp = receive();

		String smac = GetMacAddr(dp.getData());

		close();

		return smac;

	}

	protected final DatagramPacket send(final byte[] bytes) throws IOException

	{

		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(sRemoteAddr), iRemotePort);

		ds.send(dp);

		return dp;

	}

	protected final DatagramPacket receive() throws Exception

	{

		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

		ds.receive(dp);

		return dp;

	}

	protected byte[] GetQueryCmd() throws Exception

	{

		byte[] t_ns = new byte[50];

		t_ns[0] = 0x00;

		t_ns[1] = 0x00;

		t_ns[2] = 0x00;

		t_ns[3] = 0x10;

		t_ns[4] = 0x00;

		t_ns[5] = 0x01;

		t_ns[6] = 0x00;

		t_ns[7] = 0x00;

		t_ns[8] = 0x00;

		t_ns[9] = 0x00;

		t_ns[10] = 0x00;

		t_ns[11] = 0x00;

		t_ns[12] = 0x20;

		t_ns[13] = 0x43;

		t_ns[14] = 0x4B;

		for (int i = 15; i < 45; i++)

		{

			t_ns[i] = 0x41;

		}

		t_ns[45] = 0x00;

		t_ns[46] = 0x00;

		t_ns[47] = 0x21;

		t_ns[48] = 0x00;

		t_ns[49] = 0x01;

		return t_ns;

	}

	protected final String GetMacAddr(byte[] brevdata) throws Exception

	{

		int i = brevdata[56] * 18 + 56;

		String sAddr = "";

		StringBuffer sb = new StringBuffer(17);

		for (int j = 1; j < 7; j++)

		{

			sAddr = Integer.toHexString(0xFF & brevdata[i + j]);

			if (sAddr.length() < 2)

			{

				sb.append(0);

			}

			sb.append(sAddr.toUpperCase());

			if (j < 6)
				sb.append(':');

		}

		return sb.toString();

	}

	protected final void close()

	{

		try

		{

			ds.close();

		}

		catch (Exception ex) {

			ex.printStackTrace();

		}

	}
	
	public static void main(String args[]) throws Exception{  
        UdpGetClientMacAddr umac=new UdpGetClientMacAddr("192.168.9.95");  
        //---长时间获取不到MAC地址则放弃
        ExecutorService exec = Executors.newFixedThreadPool(1);
        Callable<String> call = new Callable<String>() {
            public String call() throws Exception {
                return umac.GetRemoteMacAddr();
            }
        };
        try {
            Future<String> future = exec.submit(call);
            String smac = future.get(1000 * 1, TimeUnit.MILLISECONDS);
            System.out.println(smac);
        } catch (TimeoutException ex) {
            ex.printStackTrace();
        }
        // 关闭线程池  
        exec.shutdownNow();
    } 

}
