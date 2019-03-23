#include<iostream>
#include<vector>
#include<math.h>
#include <sstream>
#include<fstream>
#include "ass4header.h"

using namespace std;
VECTOR3D ::VECTOR3D()
{

}
VECTOR3D ::VECTOR3D(float x1,float y1,float z1)
{
x=x1;
y=y1;
z=z1;
}
VECTOR3D ::~VECTOR3D()
{
}
float VECTOR3D ::get_x()
{
   return x;
}
float VECTOR3D ::get_z()
{
    return z;
}
float VECTOR3D ::get_y()
{
    return y;
}

VECTOR3D VECTOR3D::cross(VECTOR3D b)
{
    VECTOR3D crossab(y*b.get_z()-b.get_y()*z,-(x*b.get_z()-z*b.get_x()),x*b.get_y()-y*b.get_x());
    return crossab;

}
float VECTOR3D::dot(VECTOR3D b)
{

float *sum_dot=new float((x*b.get_x())+(y*b.get_y())+(z*b.get_z()));
return *sum_dot;

}
float VECTOR3D::norm(void)
{
  float magnitude=(sqrt(pow(x,2)+pow(y,2)+pow(z,2)));
  return magnitude;
}

VECTOR3D operator-(const VECTOR3D& obj,const VECTOR3D& obj1)
{
   VECTOR3D temp(obj.x-obj1.x,obj.y-obj1.y,obj.z-obj1.z);
   return temp;
}
ostream& operator<<(ostream& os,const VECTOR3D &obj)
{
    os<<obj.x<<" "<<obj.y<<" "<<obj.z;
}
Point::Point()
{

}
Point ::Point(VECTOR3D v1)
{
v=v1;
}
Point ::~Point()
{

}
void Point::set_index(int i)
{
    index=i;
}
int Point::get_index()
{
    return index;
}
VECTOR3D Point ::get_v()
{
    return v;
}
void Point ::set_v(VECTOR3D v1)
{
    v=v1;
}
float Point ::dist(Point * p2)
{

    float x1=(*p2).get_v().get_x();
    float  y1=(*p2).get_v().get_y();
    float z1=(*p2).get_v().get_z();

    return sqrt(pow(v.get_x()-x1,2)+pow(v.get_y()-y1,2)+pow(v.get_z()-z1,2));
}

Line ::Line ()
{

}
Line ::Line (Point *a,Point *b)
{
p1=a;
p2=b;
}
Line ::~Line()
{

}
void Line ::set_point1(Point *a)
{
    p1=a;
}
void Line ::set_point2(Point *b)
{
    p2=b;
}
Point* Line ::get_point1()
{
    return p1;
}
Point* Line ::get_point2()
{
    return p2;
}
Node::Node()
{

}
Node::Node(VECTOR3D v1): Point(v1)
{

}
Node::~Node()
{

}

ostream& operator<<(ostream& os,Node& n)
 {
     cout<<n.get_v().get_x()<<" "<<n.get_v().get_y()<<" "<<n.get_v().get_z();
 }
Edge::Edge()
{

}
Edge::Edge(Node* a,Node* b):n1(a),n2(b)
{

}
Edge::~Edge()
{

}
Node* Edge::get_n1()
{
    return n1;
}
Node* Edge::get_n2()
{
    return n2;
}
Polygon::Polygon()
{

}
Polygon::~Polygon()
{

}
void Polygon::add_Point(Point *a)
{
    pt_list.push_back(a);
}
vector<Point*> Polygon::get_ptlist()
{
    return pt_list;
}
Triangle::Triangle()
{

}
Triangle::~Triangle()
{

}
float find_minimum_deviation(Point *a,Point *b,Point *c)
{
  VECTOR3D a1=b->get_v()-a->get_v();
  VECTOR3D b1=b->get_v()-c->get_v();
  float cos=a1.dot(b1)/(a1.norm()*b1.norm());
  float sin=(a1.cross(b1).norm())/(a1.norm()*b1.norm());

  return acos(cos);
}
float find_angle(Point *a,Point *b,Point *c)
{


  VECTOR3D a1=a->get_v()-b->get_v();
  VECTOR3D b1=c->get_v()-b->get_v();
  float cos=a1.dot(b1)/(a1.norm()*b1.norm());
  float sin=(a1.cross(b1).norm())/(a1.norm()*b1.norm());

  return acos(cos);
}
float Triangle::findArea()
{
float a=get_ptlist()[0]->dist(get_ptlist()[1]);
float b=get_ptlist()[1]->dist(get_ptlist()[2]);
float c=get_ptlist()[2]->dist(get_ptlist()[0]);
float s=(a+b+c)/2;
float area=sqrt(s*(s-a)*(s-b)*(s-c));
return a;
}
VECTOR3D Triangle::findNormal()
{
    VECTOR3D a=(get_ptlist()[0]->get_v())-(get_ptlist()[1]->get_v());
    VECTOR3D b=(get_ptlist()[0]->get_v())-(get_ptlist()[2]->get_v());
    return a.cross(b);
}
Quadrilateral::Quadrilateral()
{

}
Quadrilateral::~Quadrilateral()
{

}
float Quadrilateral::findArea()
{
float a=get_ptlist()[0]->dist(get_ptlist()[1]);
float b=get_ptlist()[1]->dist(get_ptlist()[2]);
float c=get_ptlist()[2]->dist(get_ptlist()[0]);
float s=(a+b+c)/2;
float area1=sqrt(s*(s-a)*(s-b)*(s-c));
a=get_ptlist()[2]->dist(get_ptlist()[3]);
b=get_ptlist()[3]->dist(get_ptlist()[0]);
c=get_ptlist()[0]->dist(get_ptlist()[2]);
s=(a+b+c)/2;
float area2=sqrt(s*(s-a)*(s-b)*(s-c));
return area1+area2;
}
VECTOR3D Quadrilateral::findNormal()
{
    VECTOR3D a=(get_ptlist()[0]->get_v())-(get_ptlist()[2]->get_v());
    VECTOR3D b=(get_ptlist()[1]->get_v())-(get_ptlist()[3]->get_v());
    return a.cross(b);
}
Mesh::Mesh()
{

}
Mesh::~Mesh()
{

}
void Mesh::add_polygon(Polygon *p)
{
    p_list.push_back(p);
}
vector<Polygon*> Mesh::get_polylist()
{
    return p_list;
}
int Mesh::find_nonconvexpolygons()
    {
        float sum_angles=0;
        int count=0;
        for(int i=0;i<p_list.size();i++)
        {
            if(p_list[i]->get_ptlist().size()==4)
            {
                for(int j=0;j<p_list[i]->get_ptlist().size();j++)
                {   if(j==p_list[i]->get_ptlist().size()-2)
                    {
                        sum_angles=sum_angles+find_angle(p_list[i]->get_ptlist()[j],p_list[i]->get_ptlist()[j+1],p_list[i]->get_ptlist()[0]);
                    }
                    else if(j==p_list[i]->get_ptlist().size()-1)
                    {
                        sum_angles=sum_angles+find_angle(p_list[i]->get_ptlist()[j],p_list[i]->get_ptlist()[0],p_list[i]->get_ptlist()[1]);
                    }

                    else
                    {
                     sum_angles=sum_angles+find_angle(p_list[i]->get_ptlist()[j],p_list[i]->get_ptlist()[j+1],p_list[i]->get_ptlist()[j+2]);
                    }
                }

            if(sum_angles<2*((22.0)/7))
            {
                count++;
            }
            sum_angles=0;
            }

        }
        return count;
    }
int isthere(vector<Node*> list,Node* a)
{
    int k=0;
    for(int i=0;i<list.size();i++)
    {
        if(list[i]==a)
        {
            k=1;
        }
    }
    return k;
}

void Graph::add_node(Node *n1)
{
    nodes.push_back(n1);
}

void Graph::add_edge(Edge *e1)
{
    edges.push_back(e1);
}

vector<Node*> Graph::get_nodes()
{
    return nodes;
}

vector<Edge*> Graph::get_edges()
{
    return edges;
}

Node* Graph ::min_dist(Node *n1)
{

    Node *temp=nodes[0];
    float min=(*n1).dist(nodes[0]);
    for(int i=0;i<nodes.size();i++)
    {

        if(((*n1).dist(nodes[i])<min))
        {
            min=(*n1).dist(nodes[i]);
            temp=nodes[i];
        }

    }

    return temp;
}
vector<Node*> Graph ::connected_nodes(Node *n1,Node *n2)
{

    vector<Node*> nodes;
    for(int i=0;i<edges.size();i++)
    {
       if((*n1).dist((*edges[i]).get_n1())==0 && isthere(nodes,(*edges[i]).get_n2())==0 && (*edges[i]).get_n2()->dist(n2)!=0)
       {
           nodes.push_back((*edges[i]).get_n2());
       }
       else if((*n1).dist((*edges[i]).get_n2())==0 && isthere(nodes,(*edges[i]).get_n1())==0  && (*edges[i]).get_n1()->dist(n2)!=0)
       {
           nodes.push_back((*edges[i]).get_n1());
       }
    }


    return nodes;

}

ostream& operator<<(ostream& os,Graph &g)
{
    os<<"Nodes in grccaph:"<<endl;
    for(int i=0;i<g.get_nodes().size();i++)
    {
       os<<(*g.nodes[i]).get_v()<<endl;

    }
    os<<endl;
    os<<"Edges in graph:"<<endl;
    for(int i=0;i<g.get_edges().size();i++)
    {
        os<<(*(*g.edges[i]).get_n1()).get_v()<<"---";
        os<<(*(*g.edges[i]).get_n2()).get_v()<<endl;
    }
}

void Graph::crease(Node *a)
{
vector<Node*> list;
Node *temp=a;
int count=1;
Node* temp1=min_dist(a);
Node* temp2=connected_nodes(temp1,temp)[0];
float min=find_minimum_deviation(a,temp1,temp2);
list.push_back(temp1);

while(count<=8)
{

    for(int i=0;i<connected_nodes(temp1,temp).size();i++)
    {
      if(min>find_minimum_deviation(temp,temp1,connected_nodes(temp1,temp)[i]))
      {

          min=find_minimum_deviation(temp,temp1,connected_nodes(temp1,temp)[i]);
          temp2=connected_nodes(temp1,temp)[i];

        }

    }

       temp=temp1;
       temp1=temp2;

       temp2=connected_nodes(temp1,temp)[0];

       if(isthere(list,temp1)==0)
       {
         list.push_back(temp1);
       }
    else if(isthere(list,temp1)==1)
    {
          list.push_back(temp1);
          break;
    }
    count++;
     min=find_minimum_deviation(temp,temp1,temp2);
    }
for(int i=0;i<list.size();i++)
{
    if(i!=list.size()-1)
    {
    cout<<list[i]->get_index()<<" ";

    }
    else
    {
        cout<<list[i]->get_index();
        cout<<endl;
    }
}

}
int isedge(Edge *e,vector<Edge*> list)
{
    int k=0;
for(int i=0;i<list.size();i++)
{

 if((list[i]->get_n1()->dist(e->get_n1())==0 && list[i]->get_n2()->dist(e->get_n2())==0) || (list[i]->get_n1()->dist(e->get_n2())==0 && list[i]->get_n2()->dist(e->get_n1())==0))
 {
     k=1;
 }
    return k;
}
}

int main()
{
    Graph graph;
    Mesh mesh;
    char hash;
    int n;
    ifstream file_read;
    file_read.open("input4.txt");
    file_read>>hash;
    file_read>>n;
    Point *p;
    VECTOR3D *v;
    vector<Point*> point_list;
    float x,y,z,t;
    for(int i=0;i<n;i++)
    {
        file_read>>x>>y>>z;
       v=new VECTOR3D(x,y,z);
       p=new Point(*v);
       p->set_index(i);
       point_list.push_back(p);
    }
   string l_input;
   file_read>>hash;
    istringstream is( l_input );
    int num,count=0;

    while(1)
    {
        getline(file_read,l_input);
        if(l_input[0]=='#')
        {
            n=l_input[3];
            break;
        }
        istringstream is( l_input );
        while( is >> num ) {
            count++;
            if(count==1)
            {
              x=num;
            }
            if(count==2)
            {
              y=num;
            }
            if(count==3)
            {
              z=num;
            }
            if(count==4)
            {
              t=num;

            }
       }
       if(count==3)
       {
           Triangle *tptr=new Triangle();
           tptr->add_Point(point_list[x]);
           tptr->add_Point(point_list[y]);
           tptr->add_Point(point_list[z]);
           mesh.add_polygon(tptr);
       }
       else if(count==4)
       {

           Quadrilateral *qptr=new Quadrilateral();
           qptr->add_Point(point_list[x]);
           qptr->add_Point(point_list[y]);
           qptr->add_Point(point_list[z]);
           qptr->add_Point(point_list[t]);
           mesh.add_polygon(qptr);
       }

       count=0;
    }
  for(int i=0;i<point_list.size();i++)
  {
      graph.add_node((Node*)point_list[i]);
  }
  Edge *E;

  for(int i=0;i<mesh.get_polylist().size();i++)
  {
      for(int j=0;j<mesh.get_polylist()[i]->get_ptlist().size();j++){

          if(j==mesh.get_polylist()[i]->get_ptlist().size()-1)
          {

            E=new Edge((Node*)mesh.get_polylist()[i]->get_ptlist()[j],(Node*)mesh.get_polylist()[i]->get_ptlist()[0]);
          }
          else
          {

          E=new Edge((Node*)mesh.get_polylist()[i]->get_ptlist()[j],(Node*)mesh.get_polylist()[i]->get_ptlist()[j+1]);

        }


        graph.add_edge(E);;
    }

  }
  file_read>>x>>y>>z;

  v=new VECTOR3D(x,y,z);
  Node* a=new Node(*v);


  graph.crease(a);
  file_read>>x>>y>>z;

  v=new VECTOR3D(x,y,z);
  a=new Node(*v);


  graph.crease(a);
  cout<<mesh.find_nonconvexpolygons()<<endl;
  file_read>>hash;
  file_read.close();
}
