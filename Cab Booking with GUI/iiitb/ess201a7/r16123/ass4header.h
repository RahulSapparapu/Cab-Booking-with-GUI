using namespace std;

#ifndef graph_mesh_header_h_
#define graph_mesh_header_h_

#include<iostream>
#include<vector>
#include<math.h>

class VECTOR3D    
{
private:
   float x,y,z;  
public:
   VECTOR3D(); 
   VECTOR3D(float x,float y,float z); 
   
    ~VECTOR3D(); 
    friend ostream& operator<<(ostream& os,const VECTOR3D &obj);
    friend VECTOR3D operator-(const VECTOR3D& obj,const VECTOR3D& obj1);
    
   float get_x(); 
   
   float get_y(); 
  
   float get_z(); 
   
   VECTOR3D cross(VECTOR3D b);
   float norm(void);
   float dot(VECTOR3D b); 
  
};
class Point 
{
private:
    VECTOR3D v; 
    int index;
public:  
    Point();  
    Point(VECTOR3D v1); 
    ~Point(); 
    void set_index(int i);
    int get_index();
    VECTOR3D get_v();
    void set_v(VECTOR3D v1);
    float dist(Point *p2); 

};

class Line 
{
private:       
Point *p1,*p2; 
int index1,index2;
public:
Line();
Line (Point *a,Point *b); 
~Line(); 
void set_point1(Point *a);
void set_point2(Point *b);
Point* get_point1(); 

Point* get_point2(); 

};

class Node:public Point
{
public:
    Node();
    Node(VECTOR3D v1);
    ~Node();
   friend ostream& operator<<(ostream& os,Node& n);

};
class Edge:public Line
{
private:
    Node *n1,*n2;
public:
    Edge();
    Edge(Node* a,Node* b);
    ~Edge();
    Node* get_n1();
    Node* get_n2();
  
};


class Polygon
{
private:
    vector<Point*> pt_list;
public:
    Polygon();
    ~Polygon();
    void add_Point(Point* p1);
    vector<Point*> get_ptlist();
   
    float findArea();
    float findNormal();    
};
class Triangle:public Polygon
{

public:
    Triangle();
   
    ~Triangle();
  
    float findArea();
    VECTOR3D findNormal();
};    

class Quadrilateral:public Polygon
{
private:
    Point *p1,*p2,*p3,*p4;
public:
    Quadrilateral();
  
    ~Quadrilateral();
  
    float findArea();
    VECTOR3D findNormal();
}; 
class Mesh
{
private:
    vector<Polygon*> p_list;
public:
    Mesh();
    ~Mesh();
    void add_polygon(Polygon *p);
    vector<Polygon*> get_polylist();
    int find_nonconvexpolygons();
    
};
class Graph 
{
private:
    vector<Node*> nodes; 
    vector<Edge*>  edges; 
public:
    friend ostream& operator<<(ostream& os,Graph &obj);
    friend istream& operator>>(istream& is,Graph &obj);
    Graph operator+( Graph &g);
    Graph operator-( Graph &g);
   
void isvalid();

void add_node(Node *n1); 

void add_edge(Edge *e1); 

vector<Node*> get_nodes(); 

vector<Edge*> get_edges(); 


Node* min_dist(Node *p1); 
vector<Node*> connected_nodes(Node *n1,Node *n2);
float Sum(Point *p1); 
bool check_point(Point *p1); 
void crease(Node *n1);
};
#endif
