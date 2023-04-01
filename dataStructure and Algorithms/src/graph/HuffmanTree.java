package graph;

import java.io.*;
import java.util.*;

/**
 * 霍夫曼树，其中节点的data值，作为霍夫曼树节点的权值,char为其保存的字符
 *
 * @author goodtime
 * @create 2020-02-08 10:55 下午
 */
public class HuffmanTree {

    //传入的数组，视为每个节点的权值，根据权值来生成一颗符合要求的赫夫曼树，并返回根节点
    public HuffmanTreeNode create(int[] weights) {

        ArrayList<HuffmanTreeNode> nodes = new ArrayList<HuffmanTreeNode>();

        //第0步，根据传入的数组创建节点,并放到ArrayList中
        for (int i = 0; i < weights.length; i++) {
            HuffmanTreeNode huffmanTreeNode = new HuffmanTreeNode(weights[i]);
            nodes.add(huffmanTreeNode);
        }

        link(nodes);

        return nodes.get(0);//最后剩下的一个节点就是根节点
    }

    //抽取方法，这个方法可以让传入的nodes数组生成一棵霍夫曼树，返回的nodes只有一个元素，就是霍夫曼树的根节点
    private void link(ArrayList<HuffmanTreeNode> nodes) {

        while (nodes.size() > 1) {//当其中的节点数大于1，总是能形成新的二叉树

            //第一步，让霍夫曼树的节点类实现comparable接口，方便我们对其进行排序，(以节点中的权值从小到大排)
            Collections.sort(nodes);

            //第二步，取出根节点权值最小的两颗二叉树，组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和

            HuffmanTreeNode left = nodes.get(0);
            HuffmanTreeNode right = nodes.get(1);

            HuffmanTreeNode huffmanTreeNode = new HuffmanTreeNode(nodes.get(0), nodes.get(1));

            huffmanTreeNode.weight = left.weight + right.weight;

            //第三步，移除旧的两个权值最小的节点，将这颗新的二叉树，以根节点的权值加入到原来的排序中再次排序（当然，组成这个新二叉树两个权值最小的节点就不参与排序了）
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(huffmanTreeNode);

            //不断重复123步即可
        }
    }


    //压缩传入的数据，返回解码表
    public ArrayList zip(String data) {

        char[] chars = new char[data.length()];

        data.getChars(0, data.length(), chars, 0);//将原string变成一个字符数组


        //第一步，用hashmap来统计每个字符出现的次数
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (hashMap.containsKey(chars[i])) {
                Integer count = hashMap.get(chars[i]);
                hashMap.put(chars[i], ++count);
            } else {
                hashMap.put(chars[i], 1);
            }
        }
        ArrayList<HuffmanTreeNode> nodes = new ArrayList<>();
        //第二步，创建新的霍夫曼树节点，装入字符和其出现次数（也即是权值）
        Set<Character> characters = hashMap.keySet();

        for (Character a : characters) {
            nodes.add(new HuffmanTreeNode(hashMap.get(a), a));
        }
        //第三步，让节点生成一棵霍夫曼树,最后nodes只会保留一位，就是新生成霍夫曼树的根节点
        link(nodes);

        HuffmanTreeNode root = nodes.get(0);


        //第四步，前序遍历，根据生成的霍夫曼树，通过左边0右边1的方式，生成每个节点对应的编码表

        inOrderTraversal(root);//重载一下inOrderTraversal，方便遍历，也方便调用

        //第五步，将原来的数据，根据编码表，编码成一个字符都为0与1的字符串

        StringBuilder encodingResult = new StringBuilder();//得到的是原来的字符串对应的赫夫曼编码

        for (int i = 0; i < chars.length; i++) {
            encodingResult.append(encodingTable.get(chars[i])); //encodingTable其中保存的就是编码表
        }

        //第六步，计算byte数组的长度，准备用byte数组装压缩后的数据
        // 现在得到的encodingResult，比原来的字符串长太多（反而更占空间，字符串每个字符都要占一个char，也就是两个字节），我们需要对其进行压缩，因为我们的encodingResult
        //每一位都是0和1，所以，将它压缩成一个byte数组，每八位对应一个byte数(一个byte占一个字节)，这样就能达到压缩数据的目的


        int bytesLength = encodingResult.length() % 8 == 0 ? encodingResult.length() / 8 : encodingResult.length() / 8 + 1;

        byte[] result = new byte[bytesLength];
        int index = 0;

        for (int i = 0; i < encodingResult.length(); i += 8) {
            if (i + 7 < encodingResult.length()) {
                //记得强转为byte，默认是int
                result[index] = (byte) Integer.parseInt(encodingResult.substring(i, i + 8), 2);
                index++;
            } else {//截取从i到结尾的子字符串，形成一个byte的数
                //这里必须认真处理，要不编码后无法进行解码，我的思路是：如果最后不够8位数的这几位数，最高位是0（比如剩下0101），那么byte剩下的四位数组
                //按1补齐，也就是成为11110101，如果最高位是1（比如剩下1010），那么高位按0补齐，比如00001010,这样可以方便解码时候进行操作。
                String substring = encodingResult.substring(i);
                if (substring.charAt(0) == '0') {
                    int length = substring.length();
                    for (int j = 0; j < 8 - length; j++) {
                        substring = "1" + substring;
                    }
                    result[index] = (byte) Integer.parseInt(substring, 2);
                } else {
                    result[index] = (byte) Integer.parseInt(encodingResult.substring(i), 2);//这样操作，其实就是高位补0，我们什么也不用做
                }
            }
        }

        ArrayList arrayList = new ArrayList();//封装返回结果，0为返回结果，1为返回的编码表
        arrayList.add(result);
        arrayList.add(encodingTable);
        return arrayList;


        //自定义一个结果类，里面封装了对应数据的编码表和编码结果，方便解码
    }

    //解压传入的数据，返回原字符串
    public String unzip(ArrayList arrayList) {
        byte[] data = (byte[]) arrayList.get(0);//待解压的数据
        HashMap<Character, String> table = (HashMap<Character, String>) arrayList.get(1);//解码表，和编码表一样

        //第一步，将data数组转换成二进制表示的字符串，然后存入一个字符串中

        StringBuilder decodingString = new StringBuilder();

        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] < 0) {
                decodingString.append(Integer.toBinaryString(Byte.toUnsignedInt(data[i])));//借用Integer的toBinaryString方法完成转换
            } else {//上面的原生方法，坑爹的在于，如果高位是0（也就是data为正数），默认是不会输出成字符串的,需要另做处理，这里用了简单的拼串操作
                String s = Integer.toBinaryString(Byte.toUnsignedInt(data[i]));
                for (int j = 0; j < 8 - s.length(); j++) {
                    s = "0" + s;
                }
                decodingString.append(s);
            }
        }
        //我们还剩最后一个data数组中的byte未处理，按照我们的编码方式，如果原来编码结果的高位为0，我们高位补了1，如果原来编码结果的高位为1，我们高位补了0

        byte lastData = data[data.length - 1];
        if (lastData < 0) {//如果是个负数，说明高位补了1,原来编码结果打头是0
            String s = Integer.toBinaryString(Byte.toUnsignedInt(lastData));//转换字符串后，我们找第一个打头是0的字符，然后找到它后面的字符串
            int i = s.indexOf("0");
            decodingString.append(s.substring(i));

        } else {//如果是正数，则是高位补了0
            String s = Integer.toBinaryString(Byte.toUnsignedInt(lastData));//转换字符串后，我们找第一个打头是1的字符，然后找到它后面的字符串
            int i = s.indexOf("1");//这一步也可以省略，因为自带的方法默认不会转换高位的0
            decodingString.append(s.substring(i));
        }

        String decodingMe = decodingString.toString();


        //第二步，对编码表table进行调换，目的是为了后面方便查表，table的key是字符，值是编码结果，现在转换成
        //key为编码结果，value为字符

        HashMap<String, Character> decodingTable = new HashMap<String, Character>();

        Set<Map.Entry<Character, String>> entries = table.entrySet();

        for (Map.Entry<Character, String> a : entries
        ) {
            decodingTable.put(a.getValue(), a.getKey());//解码表
        }

        //第三步,因为赫夫曼编码是前缀编码，直接找前缀解码，解码后到下个前缀继续解码即可

        StringBuilder result = new StringBuilder();

        for (int i = 0, j = 1; i < decodingString.length(); i = j) {
            j = i + 1;
            while (j <= decodingString.length() && !decodingTable.containsKey(decodingString.substring(i, j))) {
                //注意：这里的j要 <= decodingString.length()，因为一般取子串都是左闭右开的
                j++;
            }
            if (j <= decodingString.length()) {
                //注意：这里的j也要 <= decodingString.length()，因为一般取子串都是左闭右开的
                result.append(decodingTable.get(decodingString.substring(i, j)));
            }
        }

        return result.toString();
    }


    private HashMap<Character, String> encodingTable = new HashMap<>();//记录编码表的map

    //因为根节点的特殊性，抽取出来对根的遍历，方便调用和递归
    private void inOrderTraversal(HuffmanTreeNode root) {
        encodingTable = new HashMap<>();//每次调用，都初始化一次，否则，如果用同一个HuffmanTree对象压缩好几个
        //字符串，它们的encodingTable一直没有重置，会互相污染
        if (root == null) {
            return;
        }

        if (root.c != 0) {//char的默认值为'\u0000'，用int表示就是0，
            // 除了叶子节点，其他节点的c值为默认值，也就是空字符,而且和字符空格的值是不同的
            encodingTable.put(root.c, "1");//即字符串是只有一个字符不断重复组成，编码成"11111..."即可
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        inOrderTraversal(root.left, "0", stringBuilder);
        inOrderTraversal(root.right, "1", stringBuilder);


    }

    //递归生成每个字符对应的编码表，保存在encodingTable中
    private void inOrderTraversal(HuffmanTreeNode root, String a, StringBuilder path) {

        StringBuilder stringBuilder = new StringBuilder(path);
        //重新拷贝一下path，再修改，如果直接修改path
        //因为传的是地址，对其的修改在递归返回的时候也会改变path的值
        stringBuilder.append(a);

        if (root == null) {
            return;
        } else {
            if (root.c != 0) {//char的默认值为'\u0000'，用int表示就是0，除了叶子节点，其他节点的c值为默认值，也就是空字符,而且和字符空格的值是不同的
                encodingTable.put(root.c, stringBuilder.toString());
            }
        }

        inOrderTraversal(root.left, "0", stringBuilder);

        inOrderTraversal(root.right, "1", stringBuilder);

    }


    /**
     * 对文件进行压缩操作,不能使用，因为我的接口传入的是string，字节输入流没有好的方法原封不动地转成string
     *
     * @param originFile 写入文件的地址
     * @param targetFile 写出文件的地址
     */
    public void fileZip(String originFile, String targetFile) {
        FileInputStream input = null;
        OutputStream output = null;
        ObjectOutputStream out = null;
        try {
            input = new FileInputStream(originFile);
            output = new FileOutputStream(targetFile);
            out = new ObjectOutputStream(output);
            int available = input.available();//估算有多少个字节
            byte[] origin = new byte[available];

            input.read(origin);

            // String s = Arrays.toString(origin);//字节转成字符，从而调用前面的方法，不能这么读，会读坏的，tostring方法加了太多东西

            StringBuilder s = new StringBuilder();

            for (int i = 0; i < origin.length; i++) {
                s.append(String.valueOf(origin[i]));
            }

            String s1 = s.toString();

            ArrayList zip = zip(s1);

            out.writeObject(zip);//一定要用对象输出流，把对象写出去，这样压缩文件下次进来的时候，就能用对象的方式解压开它


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                output.close();//一定要先关包装流，再关原始流，否则会报stream closed异常，因为关了原始流，包装流也会默认关闭
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //对文件进行解压缩操作,不能使用，因为我的接口传出的是string，字节输出流没有好的方法原封不动地转成字节数组，如果想用需要修改
    public void fileUnzip(String originFile, String targetFile) {
        FileInputStream input = null;
        ObjectInputStream buffer = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(originFile);

            buffer = new ObjectInputStream(input);

            output = new FileOutputStream(targetFile);

            ArrayList zip = (ArrayList)buffer.readObject();

            String unzip = unzip(zip);

            byte[] bytes = unzip.getBytes();

            output.write(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}