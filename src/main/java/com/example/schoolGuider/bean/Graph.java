package com.example.schoolGuider.bean;


import java.util.*;

public class Graph {
    private Vertex[] vertices; //存储图的结点
    private int[][] adjMatrix; //图的领接矩阵
    private int numOfEdges; //图边的数量
    private int count;
    public static final int MAX_NUM = 100000;
    ArrayList<ArrayList<Vertex>> paths;

    public Graph(int n) {
        if (n == 0) {
            return;
        }
        this.vertices = new Vertex[n];
        this.adjMatrix = new int[n][n];
        for (int i = 0; i < adjMatrix.length; i++) {
            Arrays.fill(adjMatrix[i], MAX_NUM);
        }
        numOfEdges = 0;
        count = n;
    }

    public Graph() {
    }

    public Graph(Vertex[] vertices, int[][] adjMatrix) {
        this.vertices = vertices;
        this.adjMatrix = adjMatrix;
        count = vertices.length;
    }

    /**
     * 插入图的结点
     */
    public void insertVertex(Vertex vertex) {
        vertices[vertex.getId()] = vertex;

    }


    /**
     * 新建一条边(带权值)
     * v1 v2为图中某个结点的索引值
     */
    public void insertEdge(int v1, int v2, int weight) {
        adjMatrix[v1][v2] = weight;
        numOfEdges++;
    }

    /**
     * 获取结点个数
     */
    public int getSize() {
        return count;
    }

    /**
     * 获取边数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 两个结点的边的权值
     */
    public int getWeight(int v1, int v2) {
        return adjMatrix[v1][v2];
    }

    /**
     * 获取某个结点
     *
     * @param index
     */
    public Vertex getVertex(int index) {
        return vertices[index];
    }

    /**
     * 打印输出图的领接矩阵
     */
    public void printGraph() {
        for (int[] row : adjMatrix
        ) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void printVertices() {
        for (Vertex vertex : vertices) {
            System.out.println(vertex.getId() + vertex.getName());
        }
    }

    public void dijkstra(int index) {
        //用于记录还未确定最小路径的结点
        boolean[] visited = new boolean[this.getSize()];
        //path[i]中所有值为true的点为index到这个点的最短路径
        boolean[][] paths = new boolean[this.getSize()][this.getSize()];
        //path数组仅存储路径上是否包括结点,因此,还需要一个list来记录进出的顺序
        ArrayList<Integer> order = new ArrayList<>();
        //如果用python或者js是不需要两个数组的
        //dist记录源点到其他点的最短路径
        //初始值即邻接矩阵中的值,注意:邻接矩阵中用无穷表示没有路!
        int[] dist = this.adjMatrix[index];
        visited[index] = true;
        order.add(index);
        //初始化path数组
        for (int i = 0; i < paths.length; i++) {
            //让起点和终点为true
            if (dist[i] != MAX_NUM) {
                paths[i][index] = true;
                paths[i][i] = true;
            }
        }

        //开始计算最短路径,共需要n-1次
        for (int i = 1; i < this.getSize(); i++) {
            int minIndex = index;
            //找出最短路径的那个点
            for (int j = 0; j < this.getSize(); j++) {
                if (!visited[j] && dist[j] < dist[minIndex]) {
                    minIndex = j;
                }
            }
            if (dist[minIndex] == MAX_NUM) {
                break;
            }
            //加入到树中
            visited[minIndex] = true;
            order.add(minIndex);
            //三角形,比较是否更新
            for (int j = 0; j < this.getSize(); j++) {
                if (!visited[j] && dist[j] > dist[minIndex] + adjMatrix[minIndex][j]) {
                    dist[j] = dist[minIndex] + adjMatrix[minIndex][j];
//                    paths[j] = paths[minIndex]; 引 用 传 递 不 可 以
                    System.arraycopy(paths[minIndex], 0, paths[j], 0, paths[j].length);
                    paths[j][j] = true;
                }
            }

        }
        for (boolean[] row : paths) {
            System.out.println(Arrays.toString(row));
        }
        printPath(paths, dist, order, index);
    }

    public void printPath(boolean[][] paths, int[] dist, List<Integer> order, int index) {
        //打印最短路径
        //需要path和visited配合使用
        StringBuffer[] sb = new StringBuffer[this.getSize()];
        for (int i = 0; i < paths.length; i++) {
            System.out.print(vertices[index].getName() + "到" + vertices[i].getName());
            sb[i] = new StringBuffer();
            if (dist[i] == MAX_NUM) {
                System.out.println("无路径");
                continue;
            } else {
                System.out.print("的路径长度是:" + dist[i] + ",具体路径为: ");
            }

            //按照order数组的顺序进行打印
            for (Integer j : order) {
                if (paths[i][j]) {
                    sb[i].append(vertices[j].getName()).append("->");
                }
            }
            System.out.println(sb[i]);
        }
    }


    public String dijkstra(Vertex start, Vertex target) {

        int startIndex = start.getId();
        int targetIndex = target.getId();
        //用于记录还未确定最小路径的结点
        boolean[] visited = new boolean[this.getSize()];
        //path[i]中所有值为true的点为index到这个点的最短路径
        boolean[][] paths = new boolean[this.getSize()][this.getSize()];
        //path数组仅存储路径上是否包括结点,因此,还需要一个list来记录进出的顺序
        ArrayList<Integer> order = new ArrayList<>();
        //如果用python或者js是不需要两个数组的
        //dist记录源点到其他点的最短路径
        //初始值即邻接矩阵中的值,注意:邻接矩阵中用无穷表示没有路!
        int[] dist = Arrays.copyOf(this.adjMatrix[startIndex], this.getSize());
        visited[startIndex] = true;
        order.add(startIndex);
        //初始化path数组
        for (int i = 0; i < paths.length; i++) {
            //让起点和终点为true
            if (dist[i] != MAX_NUM) {
                paths[i][startIndex] = true;
                paths[i][i] = true;
            }
        }

        //开始计算最短路径,共需要n-1次
        for (int i = 1; i < this.getSize(); i++) {
            int minIndex = startIndex;
            //找出最短路径的那个点
            for (int j = 0; j < this.getSize(); j++) {
                if (!visited[j] && dist[j] < dist[minIndex]) {
                    minIndex = j;
                }
            }
            if (dist[minIndex] == MAX_NUM) {
                break;
            }
            //加入到树中
            visited[minIndex] = true;
            order.add(minIndex);
            //三角形,比较是否更新
            for (int j = 0; j < this.getSize(); j++) {
                if (!visited[j] && dist[j] > dist[minIndex] + adjMatrix[minIndex][j]) {
                    dist[j] = dist[minIndex] + adjMatrix[minIndex][j];
//                    paths[j] = paths[minIndex]; 引 用 传 递 不 可 以
                    System.arraycopy(paths[minIndex], 0, paths[j], 0, paths[j].length);
                    paths[j][j] = true;
                }
            }

        }
        if (dist[targetIndex] == MAX_NUM) {
            return "无法到达!";
        } else {
            StringBuffer sb = new StringBuffer();
            for (Integer j : order) {
                if (paths[targetIndex][j]) {
                    sb.append(vertices[j].getName()).append("->");
                }
            }
            return "从" + start.getName() + "到" + target.getName() +
                    "的最短距离为:" + dist[targetIndex] + "米,推荐路线为:" + sb;
        }
    }

    /**
     * 从某点出发,不重复地游览所有景点,然后返回这个景点
     * 使用两个列表分别保存头几个结点和尾几个结点,直到所有结点空
     * 思路,while循环,每次选择一个离当前头结点最近的结点中选择一个最近的结点,再选择一个离当前尾结点最近的结点,最后把二者拼在一起
     * 即为一条完整的路径顺序
     */
    public String getUnRepeatedPath(int index) {
        ArrayList<Vertex> remainedVertices = new ArrayList<>();
        int totalDist = 0;
        StringBuffer path = new StringBuffer();
        //用于
        Queue<Vertex> startVertices = new LinkedList<>();
        Stack<Vertex> endVertices = new Stack<>();
        StringBuffer unrepeatedPath = new StringBuffer();

        unrepeatedPath.append("从").append(getVertex(index).getName()).append("出发,");
        for (Vertex vertex : vertices) {
            remainedVertices.add(vertex);
        }
        Vertex currVertex = remainedVertices.remove(index);
        startVertices.offer(currVertex);
        endVertices.push(currVertex);

        int currIndex1 = index;//当前所在位置
        int currIndex2 = index;//当前所在位置
        while (!remainedVertices.isEmpty()) {
            Vertex nearestVertex = getNearestVertex(remainedVertices, currIndex1);
            if (nearestVertex != null) {
                //处理最近的这个点
                startVertices.offer(nearestVertex);
                totalDist += getWeight(currIndex1, nearestVertex.getId());
                currIndex1 = nearestVertex.getId();
            }
            nearestVertex = getNearestVertex(remainedVertices, currIndex2);
            if (nearestVertex == null) {
                break;
            } else {
                //处理最近的这个点
                endVertices.push(nearestVertex);
                totalDist += getWeight(currIndex2, nearestVertex.getId());
                currIndex2 = nearestVertex.getId();

            }
        }

        //合并头、尾两条路径
        while (!endVertices.isEmpty()) {
            startVertices.offer(endVertices.pop());
        }
        //返回头路径结点
        unrepeatedPath.append("给您推荐的路径如下,距离大约为" + totalDist + "米:");
        startVertices.forEach(
                vertex -> {
                    unrepeatedPath.append(vertex.getName()).append("->");
                }
        );
        return unrepeatedPath.toString();
    }

    /**
     * 在剩余结点中选择一个离当前结点最近的结点,返回这个结点的下标
     */
    private Vertex getNearestVertex(ArrayList<Vertex> remainedVertices, int currIndex) {
        int dist = MAX_NUM;
        Vertex nearestVertex = null;
        //找到离当前所在位置最近的那个点
        for (Vertex item : remainedVertices) {
            if (getWeight(currIndex, item.getId()) < dist) {
                dist = getWeight(currIndex, item.getId());
                nearestVertex = item;
            }
        }
        remainedVertices.remove(nearestVertex);
        return nearestVertex;
    }


    /**
     * 从A点出发到B点,获取全部可行的路径
     */
    public ArrayList<ArrayList<Vertex>> getAllPaths(int startIndex, int endIndex) {
        paths = new ArrayList<>();
        ArrayList<Vertex> path = new ArrayList<>();
        path.add(getVertex(startIndex));
        getOnePath(startIndex,path,endIndex);
        return paths;
    }

    private void getOnePath(int startIndex, ArrayList<Vertex>parentPath, int endIndex) {
        for (int i = 0; i < getSize(); i++) {
            if (!parentPath.contains(getVertex(i)) && adjMatrix[startIndex][i] < MAX_NUM) {
                ArrayList<Vertex> path = new ArrayList<>(parentPath);
                path.add(getVertex(i));
                if (i == endIndex) {
                    paths.add(path);
                } else {
                    getOnePath(i, path,endIndex);
                }
            }
        }
    }
}