import {Component, OnInit} from '@angular/core';
import {GraphsService} from '../../api/services/graphs.service';
import NVL, {Node, Relationship} from '@neo4j-nvl/base';
import {ClickInteraction, DragNodeInteraction, PanInteraction, ZoomInteraction} from '@neo4j-nvl/interaction-handlers';
import {RelationshipLabel} from '../../api/models/relationship-label';

@Component({
  selector: 'app-graphs',
  templateUrl: './graphs.component.html',
  styleUrl: './graphs.component.scss',
  standalone: false
})
export class GraphsComponent implements OnInit {
  protected nodeInfo: NodeInfo = {
    id: '',
    label: '',
    color: ''
  };

  constructor(private graphsService: GraphsService) {
  }

  ngOnInit(): void {
    this.graphsService.findAllCustom().subscribe(graph => {
      const nodes: Node[] = this.convertNodes(graph.nodes);
      const relationships: Relationship[] = this.convertRelationships(graph.relationships);
      const container = <HTMLElement>document.getElementById('container');
      const nvl = new NVL(container, nodes, relationships,
        {
          styling: {
            defaultRelationshipColor: 'white',
          }
        });
      this.setupInteraction(nvl);
      this.hoverHandle(container, nvl, nodes);
    });
  }

  hoverHandle(container: HTMLElement, myNvl: NVL, nodes: Node[]) {
    container.addEventListener('click', (e) => {
      const { nvlTargets } = myNvl.getHits(e)
      if (nvlTargets.nodes.length > 0) {
        this.nodeInfo = {
          id: nvlTargets.nodes[0].data.id,
          label: nvlTargets.nodes[0].data.color === 'orange' ? 'ACCOUNT' : 'TRANSACTION',
          color: nvlTargets.nodes[0].data.color
        };
      }
    })
  }

  setupInteraction(myNvl: NVL) {
    new ZoomInteraction(myNvl)
    new PanInteraction(myNvl)
    new DragNodeInteraction(myNvl)
    new ClickInteraction(myNvl, { selectOnClick: true })
  }

  convertNodes(nodes: Array<{ id?: number }> | undefined): Node[] {
    const newNodes: Node[] = [];
    nodes?.forEach(node => {
      newNodes.push({ id: node.id?.toString() || '', caption: node.id?.toString() || '', captionSize: 3, size: 35, color: this.getColor(node)})
    })
    return newNodes;
  }

  getColor(node: { id?: number, label?: string }): string {
    if (node.label === 'ACCOUNT') {
      return 'orange'
    }
    return 'yellow';
  }

  private convertRelationships(relationships: Array<{ id?: number; from?: number; to?: number; label?: RelationshipLabel }> | undefined): Relationship[] {
    const newRelationships: Relationship[] = [];
    relationships?.forEach(relationship => {
      newRelationships.push({
        id: relationship.id?.toString() || '',
        from: relationship.from?.toString() || '',
        to: relationship.to?.toString() || '',
        caption: relationship.label === RelationshipLabel.Performs ? 'PERFORMS' : 'BENEFITS_TO',
        captionSize: 3
      })
    })
    return newRelationships;
  }
}

interface NodeInfo {
  id: string;
  label: string;
  color?: string;
}

